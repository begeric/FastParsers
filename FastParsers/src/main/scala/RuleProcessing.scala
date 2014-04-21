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
  //type RuleInfo = (RuleType, RuleCode)

  abstract class RuleInfo(val typ: RuleType,val code: RuleCode)
  case class ParamsRule(override val typ: RuleType, rawparams: List[c.Tree], override val code: RuleCode) extends RuleInfo(typ,code)
  case class Rule(override val typ: RuleType, override val code: RuleCode) extends RuleInfo(typ,code)
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
trait InlineRules extends MapRules {

  import c.universe._
  import c.universe.internal._

  override def process(rules: HashMap[String, RuleInfo]) = {
    expandRules(super.process(rules))
  }

  def expandRules(rulesMap: HashMap[String, RuleInfo]) = {
    val expandedRulesMap = new HashMap[String, RuleInfo]()
    for (k <- rulesMap.keys) {
      val rule = rulesMap(k)
      val newRuleCode = expandCallRule(rule.code, rulesMap, List(k))
      val newRule = rule match {
        case r: Rule        => r.copy(code = newRuleCode)
        case r: ParamsRule  => r.copy(code = newRuleCode)
      }
      expandedRulesMap += ((k, newRule))
    }
    expandedRulesMap
  }

  def substitute(symbol: Symbol, value: c.Tree, in: c.Tree) = new Transformer {
    override def transform(tree: c.Tree): c.Tree =
      if (tree.symbol == symbol) value
      else  super.transform(tree)
  }.transform(in)

  def getInnerTypeOf[T : TypeTag](typ: c.Type): Option[c.Type] = typ match {
    case TypeRef(_, _, List(z)) if typ <:< typeOf[T] => Some(z)
    case _ => None
  }

 /**
  * Traverse the rule tree and expand the rule when it can
  */
 def expandCallRule(tree: c.Tree, rulesMap: HashMap[String, RuleInfo], rulesPath: List[String]): c.Tree = tree match {
   case q"if($c) $a else $b" => q"if($c) ${expandCallRule(a,rulesMap,rulesPath)} else ${expandCallRule(b,rulesMap,rulesPath)}"
   /*case q"$a.${b: TermName}" if a.tpe <:< typeOf[FinalFastParserImpl] =>  //TODO put comments
     c.typecheck(a).tpe.members.find(_.name == b) match {
       case Some(rule) => rule.typeSignature.resultType match {
         case AnnotatedType(annotations,typ) => annotations.find(_.tree.tpe =:= typeOf[saveAST]) match {
             case Some(annot) =>
               val code = annot.tree.children(1)
               val codetyp = getInnerTypeOf[ParseResult[_]](typ) getOrElse c.abort(c.enclosingPosition, "wrong type for " + show(typ) + " during foreign call expansion")
               expandCallRule(code,rulesMap,rulesPath)
               q"compound[$codetyp](${expandCallRule(code, rulesMap, rulesPath)})"    //name :: rulesPath ?
             case _ => c.abort(c.enclosingPosition,show(annotations.head.tree.tpe.typeSymbol.fullName) + " : is not saveAST")
           }
         case typ => c.abort(c.enclosingPosition,"error : " + show(typ) + " should be an annotated type")
       }
       case _ => c.abort(c.enclosingPosition, show(b) + " does not exists in" + show(a))  //should never happend actually  because wouldnt compile
     } */
   case q"$a.$m[..$d](..$b)" =>
     val callee = expandCallRule(a, rulesMap, rulesPath)
     val args = b.map(expandCallRule(_, rulesMap, rulesPath))
     q"$callee.$m[..$d](..$args)"
   /*case q"${ruleCall: TermName}(..$args)" =>
     val name = ruleCall.toString
     rulesMap.get(name) match {
       case Some(_: Rule) => c.abort(c.enclosingPosition,name + " called with parameters")
       case Some(ParamsRule(typ, params, code)) =>
         if (params.size != args.size)
           c.abort(c.enclosingPosition,"not enough parameters for rule " + name)
         else if (!rulesPath.contains(name)) {
           val substituted = params.zip(args).foldLeft(code){(acc,c) => substitute(c._1.symbol,c._2,acc)}
           q"compound[$typ](${expandCallRule(substituted, rulesMap, name :: rulesPath)})"
         }
         else
          q"call[$typ]($ruleCall, ..$args)"
       case _ => tree
     }   */
   case q"$f[..$d](..$b)" =>
     val callee = expandCallRule(f, rulesMap, rulesPath) //because of repFold and al curried stuff
     val args = b.map(expandCallRule(_, rulesMap, rulesPath))
     q"$callee[..$d](..$args)"
   case q"$a.${f: TermName}" =>
     val callee = expandCallRule(a, rulesMap, rulesPath)
     q"$callee.$f"
   case q"${ruleCall: TermName}" =>
     val name = ruleCall.toString
     rulesMap.get(name) match {
       case Some(Rule(typ,code)) =>
         if (!rulesPath.contains(name))
           q"compound[$typ](${expandCallRule(code, rulesMap, name :: rulesPath)})"
         else
           q"call[$typ](${ruleCall.toString})"
       case Some(_: ParamsRule) =>
         c.abort(c.enclosingPosition,name + " called without parameters")
       case _ =>
         tree
     }
   case _ => tree
 }
}


/**
* Create the "final" code for each rule
*/
trait ParseRules extends MapRules {
  self: ParseInput with CombinatorImpl =>

  import c.universe._
  import c.universe.internal._
  //import c.internal.decorators._

  override def process(rules: HashMap[String, RuleInfo]) = {
   val rulesMap = super.process(rules)

   val map = new HashMap[String, RuleInfo]()

   for (k <- rulesMap.keys) {
     val rule = rulesMap(k)
     map += ((k, Rule(rule.typ, createRuleDef(k, rule))))
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
   val rulecode = rule match {
     case ParamsRule(_,params,_) =>
       c.untypecheck(q"""def $ruleName(input: $inputType, ..$params, $startPosition: Int = 0):ParseResult[${rule.typ}] = ${initInput(q"$startPosition", wrapCode)}""")
     case _:Rule =>
       val replacedTree = removeCompileTimeAnnotation(rule.code)
       q"""def $ruleName(input:$inputType,$startPosition:Int = 0):  ParseResult[${rule.typ}] @saveAST(${replacedTree}) = ${initInput(q"$startPosition", wrapCode)}"""
   }
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
     rule match {
       case ParamsRule(_,params,_) => q"def $ruleName(..$params): Parser[${rule.typ}] = ???"
       case _ =>  q"def $ruleName: Parser[${rule.typ}] = ???"
     }
   }
   //
   val tree = q"""
     class $anon extends FinalFastParserImpl {
         import scala.collection.mutable.ListBuffer
         import scala.reflect.runtime.universe._
          ..$methods
          ..$methodsEmpty
     }
     val $dmmy = 0
     new $anon
   """
   //c.abort(c.enclosingPosition, show(tree))
   tree
 }
}
