package fastparsers.framework.implementations

import scala.language.experimental.macros
import fastparsers.tools.TreeTools
import fastparsers.input.ParseInput
import scala.reflect.macros.whitebox.Context
import scala.collection.mutable.HashMap
import fastparsers.framework.ruleprocessing.{RuleCombiner, MapRules}
import fastparsers.parsers.Parser

/**
 * General trait which create the basic needs of a FastParsers implementation.
 *
 * All it does is create the map of rulenames with their code (whitout modification).
 * It must be composed with some Rule transformer which will expand the rules wich will
 * be combined to form the final object by the fastparsers.framework.ruleprocessing.RuleCombiner.
 * It must also be composed with a fastparsers.input.ParseInput to allow access on the fastparsers.input.
 */
trait BaseImpl extends TreeTools {
  self: MapRules with RuleCombiner with ParseInput =>
  val c: Context

  import c.universe._

  def FastParser(rules: c.Tree): c.Tree = {
    val map = getBasicStructure(rules)
    val transformedMap = process(map)
    combine(transformedMap)
  }

  /**
   * Expand each rule in a imperative style without considering other rules (i.e def rule2 = rule1 is not expanded to the code of rule1)
   * @return An HashMap containing (rulename, corresponging code)
   */
  private def getBasicStructure(rules: c.Tree) = {

    def getReturnType(ruleCode: c.Tree): Type = c.typecheck(ruleCode).tpe match {
      //case TypeRef(_, y, List(z)) if y.typeSignature =:= typeOf[Parser[_]] => z
      case TypeRef(_, y, List(z)) if y.fullName == "fastparsers.parsers.Parser" => z //q"Any".tpe//q"var x:${d.tpe}" //check it is a code
      case v => c.abort(c.enclosingPosition, "incorrect parser type " + show(v))
    }


    val rulesMap = new HashMap[String, RuleInfo]()
    c.typecheck(rules) match {
      case q"{..$body}" =>
        body.foreach {
          /*case q"def $name[..$t](..$params): $d = $b" =>
            rulesMap += name.toString -> RuleInfo(getReturnType(d), b, params,t)*///not supported yet
          //case q"def $name(..$params): $_.Parser[$d]" => c.abort(c.enclosingPosition, "yo")
          case q"def $name(..$params): $d = $b" =>
            rulesMap += name.toString -> RuleInfo(getReturnType(d), b, params, Nil, b)
          case q"def $name: $d = $b" =>
            rulesMap += name.toString -> RuleInfo(getReturnType(d), b, Nil, Nil, b)
          case q"()" =>
          case x => c.abort(c.enclosingPosition, "body must only contain rule definition with the following form : def ruleName = body : " + x)
        }
      case _ =>
        c.abort(c.enclosingPosition, "ill-formed body, cannot be empty") //TODO can be empty ?
    }
    rulesMap
  }
}
