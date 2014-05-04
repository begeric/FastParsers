package fastparsers.framework.ruleprocessing

import scala.reflect.macros.whitebox.Context

/**
 * Created by Eric on 22.04.14.
 */
trait RulesProcessing {
  val c: Context
  type RuleType = c.Type
  type RuleCode = c.Tree
  type ParamInfo = (c.TermName, c.Type)

  case class RuleInfo(typ: RuleType,code: RuleCode, params: List[c.Tree], typeParams: List[c.universe.TypeDef], oldCode: c.Tree)
}
