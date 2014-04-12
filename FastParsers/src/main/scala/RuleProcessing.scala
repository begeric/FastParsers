import scala.collection.mutable.{ListBuffer, HashMap}
import scala.reflect.macros.whitebox.Context

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

  override def process(rules: HashMap[String, RuleInfo]) = {
    expandRules(super.process(rules))
  }

  def expandRules(rulesMap: HashMap[String, RuleInfo]) = {
    val expandedRulesMap = new HashMap[String, RuleInfo]()
    for (k <- rulesMap.keys) {
      val ruleType = rulesMap(k).typ
      val ruleCode = rulesMap(k).code
      val rule = expandCallRule(ruleCode, rulesMap, List(k))
      expandedRulesMap += ((k, Rule(ruleType, rule)))
    }
    expandedRulesMap
  }

  /**
   * Traverse the rule tree and expand the rule when it can
   */
  def expandCallRule(tree: c.Tree, rulesMap: HashMap[String, RuleInfo], rulesPath: List[String]): c.Tree = tree match {
    case q"if($c) $a else $b" => q"if($c) ${expandCallRule(a,rulesMap,rulesPath)} else ${expandCallRule(b,rulesMap,rulesPath)}"
    case q"$a.$m[..$d](..$b)" =>
      val callee = expandCallRule(a, rulesMap, rulesPath)
      val args = b.map(expandCallRule(_, rulesMap, rulesPath))
      q"$callee.$m[..$d](..$args)"
    case q"$f[..$d](..$b)" =>
      val callee = expandCallRule(f, rulesMap, rulesPath) //because of repFold and al curried stuff
    val args = b.map(expandCallRule(_, rulesMap, rulesPath))
      q"$callee[..$d](..$args)"
    case q"$a.${f: TermName}" =>
      val callee = expandCallRule(a, rulesMap, rulesPath)
      q"$callee.$f"
    case q"${ruleCall: TermName}" =>
      val name = ruleCall.toString
      if (rulesMap.keySet.contains(name)) {
        if (!rulesPath.contains(name))
          q"compound[${rulesMap(name).typ}](${expandCallRule(rulesMap(name).code, rulesMap, name :: rulesPath)})"
        else
          q"call[${rulesMap(name).typ}]($tree)"
      }
      else
        tree
    case _ => tree
  }
}

/**
 * Create the "final" code for each rule
 */
trait ParseRules extends MapRules {
  self: ParseInput with CombinatorImpl =>

  import c.universe._

  override def process(rules: HashMap[String, RuleInfo]) = {
    val rulesMap = super.process(rules)

    val map = new HashMap[String, RuleInfo]()

    for (k <- rulesMap.keys) {
      val typ = rulesMap(k).typ
      val code = rulesMap(k).code
      map += ((k, Rule(typ, createRuleDef(k, typ, code))))
    }

    map
  }

  private def createRuleDef(name: String, retType: c.Type, code: c.Tree): c.Tree = {
    val ruleName = TermName(name)
    val startPosition = TermName(c.freshName)
    val rs = new ResultsStruct(new ListBuffer[Result]())
    val ruleCode = expand(code, rs)
    val initResults = rs.results.map(x => q"var ${x._1}:${x._2} = ${zeroValue(x._2)}")
    val tupledResults = combineResults(rs.results)

    val result = q"""ParseResult(success,msg,if (success) $tupledResults else ${zeroValue(tq"$retType")},$pos)"""

    val wrapCode =
      q"""
      var success = false
      var msg = ""
        ..$initResults
        $ruleCode
        $result
    """

    q"""def $ruleName(input:$inputType,$startPosition:Int = 0):ParseResult[$retType] = ${initInput(q"$startPosition", wrapCode)}"""
  }

}

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

    q"""
      class $anon {
          import scala.collection.mutable.ListBuffer
          import scala.reflect.runtime.universe._
          ..$methods
      }
      val $dmmy = 0
      new $anon
    """
  }
}

/**
 * Doesn't work but is supposed to print the generated code
 */
trait PrintCode extends RuleCombiner {

  import c.universe._

  override def combine(rules: HashMap[String, RuleInfo]): c.Tree = {
    val anon = TypeName(c.freshName)
    val dmmy = TermName(c.freshName)
    q"""
      import scala.reflect.runtime.universe._
      println(show(reify(${super.combine(rules)}).tree))
    """
  }
}
