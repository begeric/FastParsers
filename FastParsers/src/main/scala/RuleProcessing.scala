import scala.annotation.StaticAnnotation
import scala.collection.mutable.{ListBuffer, HashMap}
import scala.reflect.macros.whitebox.Context
import scala.tools.nsc.doc.DocParser.Tree


class saveAST(code: Any) extends StaticAnnotation {
  //def macroTransform(annottees: Any*) = macro ???
}

trait RulesProcessing {
  val c: Context
  type RuleType = c.Type
  type RuleCode = c.Tree
  type ParamInfo = (c.TermName, c.Type)
  //type RuleInfo = (RuleType, RuleCode)

  case class RuleInfo(typ: RuleType,code: RuleCode, params: List[c.Tree], typeParams: List[c.universe.TypeDef])
}

/**
 * Transform rules
 */
trait MapRules extends RulesProcessing {
  def process(rules: HashMap[String, RuleInfo]): HashMap[String, RuleInfo] = rules
}

/**
 * Combine rules
 */
trait ReduceRules extends RulesProcessing {
  def combine(rules: HashMap[String, RuleInfo]): c.Tree
}

/**
 * Inline rule calls.
 *
 * def rule1 = a ~ rule2
 * def rule2 = b ~ c
 *
 * becomes
 *
 * def rule1 = a ~ b ~ c
 * def rule2 = b ~ c
 *
 * If it cannot be inlined (recursive rules) then the rule will be simply called
 */
trait InlineRules extends MapRules { self: TreeTools =>

  import c.universe._
  import c.universe.internal._

  override def process(rules: HashMap[String, RuleInfo]) = {
    expandRules(super.process(rules))
  }

  def expandRules(rulesMap: HashMap[String, RuleInfo]) = {
    val expandedRulesMap = new HashMap[String, RuleInfo]()
    for (k <- rulesMap.keys) {
      val rule = rulesMap(k)
      val parserParamsMap = rule.params.map{case ValDef(_,name,tpt,_) => (name,tpt.tpe)}
                          .filter(_._2 <:< typeOf[Parser[_]])
                          .map(x => (x._1,getInnerTypeOf[Parser[_]](x._2).get)) //TODO what do you mean its super ugly ?
      val newRuleCode = expandCallRule(rule.code, parserParamsMap, rulesMap, List(k))
      expandedRulesMap += ((k, rule.copy(code = newRuleCode)))
    }
    expandedRulesMap
  }



 /**
  * Traverse the rule tree and expand the rule when it can
  */
 def expandCallRule(tree: c.Tree, paramsMap: List[ParamInfo], rulesMap: HashMap[String, RuleInfo], rulesPath: List[String]): c.Tree = {

   def expandRuleCall(ruleName: TermName, args: List[c.Tree]): Option[c.Tree] = {
     val name = ruleName.toString
     rulesMap.get(name) match {//TODO what about overloading ?
       case Some(RuleInfo(typ, code, params, typParams)) =>
         if (params.size != args.size)
           c.abort(c.enclosingPosition,"not enough parameters for rule " + name + " : " + show(params) + " : " + show(args))
         else if (!rulesPath.contains(name)) {
           val substituted = params.zip(args).foldLeft(code){(acc,c) => substituteSymbol(c._1.symbol, _ => c._2,acc)}
           Some(q"compound[$typ](${expandCallRule(substituted,paramsMap, rulesMap, name :: rulesPath)})")
         }
         else
           Some(q"call[$typ]($name, ..$args)")
       case _ => None
     }
   }

   def expandParamCall(paramName: TermName, args: List[c.Tree]) : Option[c.Tree] = paramsMap.find(_._1 == paramName) match {
     case Some((_, tpe)) => Some(q"call[${tpe}](${paramName.toString}, ..$args)")//TODO check for param errors
     case None =>  None
   }

   tree match {
     case q"if($c) $a else $b" => q"if($c) ${expandCallRule(a, paramsMap,rulesMap,rulesPath)} else ${expandCallRule(b, paramsMap,rulesMap,rulesPath)}"
     /*case q"$a.${b: TermName}" if a.tpe <:< typeOf[FinalFastParserImpl] =>  //TODO put comments
       c.typecheck(a).tpe.members.find(x => x.name == b) match {  // && x =:= typeOf[ParseResult]
         case Some(rule) => rule.typeSignature.resultType match {
           case AnnotatedType(annotations,typ) => annotations.find(_.tree.tpe =:= typeOf[saveAST]) match {
               case Some(annot) =>
                 val code = annot.tree.children(1)
                 val codetyp = getInnerTypeOf[ParseResult[_]](typ) getOrElse c.abort(c.enclosingPosition, "wrong type for " + show(typ) + " during foreign call expansion")
                 expandCallRule(code,rulesMap,rulesPath)
                 q"compound[$codetyp](${expandCallRule(code, rulesMap, rulesPath)})"    //name :: rulesPath ?
               case _ => c.abort(c.enclosingPosition,show(annotations.head.tree.tpe.typeSymbol.fullName) + " : is not saveAST")
             }
           case typ => c.abort(c.enclosingPosition,"error : " + show(rule.typeSignature) + " should be an annotated type")
         }
         case _ => c.abort(c.enclosingPosition, show(b) + " does not exists in" + show(a))  //should never happend actually  because wouldnt compile
       } */
     case q"$a.$m[..$d](..$b)" =>
        val callee = expandCallRule(a, paramsMap, rulesMap, rulesPath)
        val args = b.map(expandCallRule(_, paramsMap, rulesMap, rulesPath))
        q"$callee.$m[..$d](..$args)"
     case q"${ruleName: TermName}[$_](..$args)" =>
       expandParamCall(ruleName, args) getOrElse (expandRuleCall(ruleName,args) getOrElse tree)
     case q"${ruleName: TermName}(..$args)" =>
        expandRuleCall(ruleName,args) getOrElse tree
     case q"$f[..$d](..$b)" =>
        val callee = expandCallRule(f, paramsMap, rulesMap, rulesPath) //because of repFold and al curried stuff
        val args = b.map(expandCallRule(_, paramsMap, rulesMap, rulesPath))
        q"$callee[..$d](..$args)"
     case q"$a.${f: TermName}" =>
        val callee = expandCallRule(a, paramsMap, rulesMap, rulesPath)
        q"$callee.$f"
     case q"${ruleName: TermName}" =>
       expandParamCall(ruleName, Nil) getOrElse (expandRuleCall(ruleName,Nil) getOrElse tree)
     case _ => tree
   }
 }
}


/**
* Create the "final" code for each rule
*/
trait ParseRules extends MapRules {
  self: ParseInput with CombinatorImpl with TreeTools =>

  import c.universe._
  import c.universe.internal._
  //import c.internal.decorators._

  override def process(rules: HashMap[String, RuleInfo]) = {
   val rulesMap = super.process(rules)

   val map = new HashMap[String, RuleInfo]()

   for (k <- rulesMap.keys) {
     val rule = rulesMap(k)
     map += ((k, rule.copy(code = createRuleDef(k, rule))))
   }

   map
 }

  def removeCompileTimeAnnotation(tree: c.Tree): c.Tree = new Transformer {
    override def transform(tree: c.Tree): c.Tree = tree match {
      case orig @ Select(qual, name) if orig.symbol.annotations.exists(_.tree.tpe =:= typeOf[scala.annotation.compileTimeOnly]) =>
        val newStuff = setType(setSymbol(Select(qual, name),NoSymbol), orig.tpe)
        super.transform(newStuff)
      case _ => super.transform(tree)
    }
  }.transform(c.typecheck(tree))




 private def createRuleDef(name: String, rule: RuleInfo): c.Tree = {


   val parserParams = rule.params.map{case ValDef(_,name,tpt,_) => (name,tpt.tpe)}.filter(_._2 <:< typeOf[Parser[_]])

   val ruleName = TermName(name)
   val startPosition = TermName(c.freshName)
   val rs = new ResultsStruct(new ListBuffer[Result]())
   val ruleCode = expand(rule.code, rs)
   val initResults = rs.results.map(x => q"var ${x._1}:${x._2} = ${zeroValue(x._2)}")
   val tupledResults = combineResults(rs.results)

   val result = q"""ParseResult(success,msg,if (success) $tupledResults else ${zeroValue(tq"${rule.typ}")},$pos)"""

   val wrapCode =
     q"""
     var success = false
     var msg = ""
       ..$initResults
       $ruleCode
       $result
   """

   val rewriteParams = rule.params.map{
     case valdef @ ValDef(m,n,t,v)  => getInnerTypeOf[Parser[_]](t.tpe) match {
       case Some(innerType) => ValDef(m,n,tq"($inputType, Int) => ParseResult[$innerType]",v)
       case None => valdef
     }
   }

   val replacedTree = removeCompileTimeAnnotation(rule.code)
   val allParams: List[c.Tree] = q"input: $inputType" :: (rewriteParams :+ q"val $startPosition: Int = 0")
   val rulecode = c.untypecheck(
     q" def $ruleName[..${rule.typeParams}](..$allParams):ParseResult[${rule.typ}] = ${initInput(q"$startPosition", wrapCode)}") match {
     case q"def $a[$t](..$b):$d = $e" => q"def $a[$t](..$b):$d @saveAST(${replacedTree}) = $e"
     case q"def $a(..$b):$d = $e" => q"def $a(..$b):$d @saveAST(${replacedTree}) = $e"
   }    //TODO o/w typecheck error. explain. This is retarded

   rulecode
 }

}

trait FinalFastParserImpl

/**
* Create the final parser object
*/
trait RuleCombiner extends ReduceRules {
 val c: Context

 import c.universe._

 def combine(rules: HashMap[String, RuleInfo]) = {
   val anon = TypeName(c.freshName)
   val dmmy = TermName(c.freshName) //no joke : see http://stackoverflow.com/questions/14370842/getting-a-structural-type-with-an-anonymous-classs-methods-from-a-macro

   val methods = rules.values.map(_.code)

   val methodsEmpty = rules.keySet.map{ k =>
     val rule = rules(k)
     val ruleName = TermName(k)
     rule.params match {
       case Nil =>      q"def $ruleName[..${rule.typeParams}]: Parser[${rule.typ}] = ???"
       case params =>   q"def $ruleName[..${rule.typeParams}](..${rule.params}): Parser[${rule.typ}] = ???"
     }

   }
   //
   val tree = q"""
     class $anon extends FinalFastParserImpl {
         import scala.collection.mutable.ListBuffer
         import scala.reflect.runtime.universe._
          ..$methodsEmpty
          ..$methods
     }
     val $dmmy = 0
     new $anon
   """
   //c.abort(c.enclosingPosition, show(tree))
   tree
 }
}
