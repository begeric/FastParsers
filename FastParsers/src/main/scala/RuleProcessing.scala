import scala.annotation.StaticAnnotation
import scala.collection.mutable.{ListBuffer, HashMap}
import scala.reflect.macros.whitebox.Context


class saveAST(code: Any) extends StaticAnnotation

trait RulesProcessing {
  val c: Context
  type RuleType = c.Type
  type RuleCode = c.Tree
  type ParamInfo = (c.TermName, c.Type)

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
trait InlineRules extends MapRules { self: TreeTools with ParseInput =>

  import c.universe._
  import c.universe.internal._
  //import c.internal.decorators._  doesn't wokr ?

  var anonymousNumber = 0
  def getAnonymousName = {anonymousNumber += 1; "anonymous$" + anonymousNumber}

  override def process(rules: HashMap[String, RuleInfo]) = {
    expandRules(super.process(rules))
  }

  def expandRules(rulesMap: HashMap[String, RuleInfo]) = {
    val expandedRulesMap = new HashMap[String, RuleInfo]()

    def expandRule(name: String){
      val rule = rulesMap(name)
      val newRuleCode = expandCallRule(rule.code, rule, rulesMap, expandedRulesMap, List(name)) //c.typecheck(rule.code)
      expandedRulesMap += (name -> rule.copy(code = newRuleCode))
    }

    for (k <- rulesMap.keys)
      expandRule(k)

    expandedRulesMap
  }



 /**
  * Traverse the rule tree and expand the rule when it can
  */
 def expandCallRule(tree: c.Tree,
                    enclosingRule: RuleInfo,
                    rulesMap: HashMap[String, RuleInfo],
                    expandedRules: HashMap[String, RuleInfo],
                    rulesPath: List[String]): c.Tree = {

   //same hack as for annotation stuff
   def convertParsersArgs(params: List[c.Tree]) = params.map{ p =>
     getInnerTypeOf[Parser[_]](p.tpe) match {
       case Some(innerType) => p match {
         /*case q"${ruleName : TermName}" =>
           /*c.abort(c.enclosingPosition,show(tq"($inputType,Int) => ParseResult[$innerType]".tpe))
           c.untypecheck(setSymbol(p, NoSymbol)) */
           setSymbol(p, NoSymbol) */
         case _ => //then need to create another anonymous rule
           val newCode = expandCallRule(p,enclosingRule, rulesMap, expandedRules, rulesPath)
           val newRule = RuleInfo(innerType,newCode,enclosingRule.params, enclosingRule.typeParams)
           val name = getAnonymousName
           expandedRules += name -> newRule
           setSymbol(q"${TermName(name)}", NoSymbol)
       }
       case None => p
     }
   }


   def expandRuleCall(ruleName: TermName, typeArgs: List[c.Type], args: List[c.Tree]): Option[c.Tree] = {
     val name = ruleName.toString
     rulesMap.get(name) match {//TODO what about overloading ?
       case Some(RuleInfo(typ, code, params, typParams)) =>
         if (params.size != args.size)
           c.abort(c.enclosingPosition,"not enough parameters for rule " + name + " : " + show(params) + " : " + show(args))
         else if (!rulesPath.contains(name)) {
           val substituted = subsituteParams(params.map(_.symbol), args, code)
           Some(q"compound[$typ](${expandCallRule(substituted,enclosingRule, rulesMap, expandedRules, name :: rulesPath)})")
         }
         else {
           Some(q"call[$typ](${ruleName.toString}, ..${convertParsersArgs(args)})") //TODO check that stuff
         }
       case _ => None
     }
   }

   def expandParamCall(paramName: TermName, typeArgs: List[c.Type], args: List[c.Tree]) : Option[c.Tree] =
     getParserParams(enclosingRule.params).find(_._1 == paramName) match {
     case Some((_, tpe)) =>
       Some(q"call[${tpe}](${paramName.toString}, ..$args)")//TODO check for param errors
     case None =>  None
   }

   def expandForeignCall(obj: c.Tree, ruleName: TermName, args: List[c.Tree]) =
    c.typecheck(obj).tpe.members.find(x => x.name == ruleName) match {
      case Some(rule) => rule.typeSignature.resultType match {
        case AnnotatedType(annotations,typ) => annotations.find(_.tree.tpe =:= typeOf[saveAST]) match {
          case Some(annot) =>
            val codetyp = getInnerTypeOf[ParseResult[_]](typ) getOrElse c.abort(c.enclosingPosition, "wrong type for " + show(typ) + " during foreign call expansion")
            val concatName = obj.toString + "." + ruleName.toString
            val params = rule.typeSignature.paramLists.head//cool this is for curried things i guess, really cool
            val ruleParams = params.slice(1,params.size - 1)
            if (ruleParams.size != args.size)
              c.abort(c.enclosingPosition,"not enough parameters for rule " + concatName + " : " + show(params) + " : " + show(args))
            //same logic as in normal rule call
            if (!rulesPath.contains(concatName)) { //never supposed to be false... for now
              //we have to substitute parameters AND other rule calls
              val code = annot.tree.children(1)
              def substituteCall(in: c.Tree) = new Transformer {
                override def transform(tree: c.Tree): c.Tree = tree match {
                  case q"$_.call[$t](..$a)" => q"foreignCall[$t]($obj,..$a)"
                  case _ => super.transform(tree)
                }
              }.transform(in)
              val substitued = substituteCall(subsituteParams2(ruleParams.map(_.name.toTermName), args, code))
              q"compound[$codetyp]($substitued)" //this suppose that the code is already expanded, maybe everything would be easier if it wasn't...
            }
            else
              q"foreignCall[$codetyp]($obj,${ruleName.toString},..$args)"
          case _ => c.abort(c.enclosingPosition,"unexpected behaviour during foreign call expansion") //TODO
        }
        case typ => c.abort(c.enclosingPosition,"error : " + show(rule.typeSignature) + " should be an annotated type")
      }
      case _ => c.abort(c.enclosingPosition, show(ruleName) + " does not exists in" + show(obj))  //should never happend actually  because wouldnt compile
    }

   tree match {
     case q"if($c) $a else $b" =>
       q"if($c) ${expandCallRule(a, enclosingRule,rulesMap,expandedRules,rulesPath)} else ${expandCallRule(b, enclosingRule,rulesMap,expandedRules,rulesPath)}"
     case q"$a.${b: TermName}(..$args)" if a.tpe <:< typeOf[FinalFastParserImpl] =>  //TODO put comments
       expandForeignCall(a,b,args)
     case q"$a.${b: TermName}" if a.tpe <:< typeOf[FinalFastParserImpl] =>  //TODO put comments
       expandForeignCall(a,b,Nil)
     case q"$a.$m[..$d](..$b)" =>
        val callee = expandCallRule(a, enclosingRule, rulesMap,expandedRules, rulesPath)
        val args = b.map(expandCallRule(_, enclosingRule, rulesMap,expandedRules, rulesPath))
        q"$callee.$m[..$d](..$args)"
     case q"${ruleName: TermName}[..$t](..$args)" =>
        expandParamCall(ruleName, t.map(_.tpe), args) getOrElse (expandRuleCall(ruleName, t.map(_.tpe),args) getOrElse tree)
     case q"${ruleName: TermName}(..$args)" =>
        expandRuleCall(ruleName, Nil,args) getOrElse tree
     case q"$f[..$d](..$b)" =>
        val callee = expandCallRule(f, enclosingRule, rulesMap,expandedRules, rulesPath) //because of repFold and al curried stuff
        val args = b.map(expandCallRule(_, enclosingRule, rulesMap,expandedRules, rulesPath))
        q"$callee[..$d](..$args)"
     case q"$a.${f: TermName}" =>
        val callee = expandCallRule(a, enclosingRule, rulesMap,expandedRules, rulesPath)
        q"$callee.$f"
     case q"${ruleName: TermName}[..$t]" =>
       expandParamCall(ruleName, t.map(_.tpe), Nil) getOrElse (expandRuleCall(ruleName, t.map(_.tpe),Nil) getOrElse tree)
     case q"${ruleName: TermName}" =>
       expandParamCall(ruleName, Nil, Nil) getOrElse (expandRuleCall(ruleName, Nil,Nil) getOrElse tree)
     case _ => tree
   }
 }
}


/**
* Create the "final" code for each rule
*/
trait ParseRules extends MapRules {
  self: ParseInput with ParserImplHelper with TreeTools =>

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

  def convertParsersParams(params: List[c.Tree]) = params.map{
    case valdef @ ValDef(m,n,t,v)  => getInnerTypeOf[Parser[_]](t.tpe) match {
      case Some(innerType) =>
        ValDef(m,n,tq"($inputType, Int) => ParseResult[$innerType]",v)
      case None => valdef
    }
  }

  def removeCompileTimeAnnotation(tree: c.Tree): c.Tree = new Transformer {
    override def transform(tree: c.Tree): c.Tree = tree match {
      case orig @ Select(qual, name) if orig.symbol.annotations.exists(_.tree.tpe =:= typeOf[scala.annotation.compileTimeOnly]) =>
        val newStuff = setType(setSymbol(Select(qual, name),NoSymbol), orig.tpe)
        super.transform(newStuff)
      case _ => super.transform(tree)
    }
  }.transform(c.typecheck(tree)) //typecheck or not typecheck ?


  private def createRuleDef(name: String, rule: RuleInfo): c.Tree = {
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

   val rewriteParams = convertParsersParams(rule.params)

   val replacedTree = removeCompileTimeAnnotation(c.untypecheck(rule.code))// @saveAST(${replacedTree})
    //c.abort(c.enclosingPosition, show(replacedTree))
   val allParams = q"input: $inputType" :: (rewriteParams :+ q"val $startPosition: Int = 0")
   val rulecode = c.untypecheck(
     q" def $ruleName[..${rule.typeParams}](..$allParams):ParseResult[${rule.typ}] = ${initInput(q"$startPosition", wrapCode)}" )match {
     case q"def $a[$t](..$b):$d = $e" => q"def $a[$t](..$b):$d  @saveAST(${replacedTree}) = $e"
     case q"def $a(..$b):$d = $e" => q"def $a(..$b):$d @saveAST(${replacedTree})  = $e"
   }   //TODO o/w typecheck error. explain. This is retarded  Proxy for x error
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
  import c.universe.internal._


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
   tree
 }
}
