package fastparsers.framework.ruleprocessing

import fastparsers.tools.TreeTools
import fastparsers.input.ParseInput
import scala.collection.mutable.HashMap

/**
 * Created by Eric on 23.04.14.
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
trait RulesInliner extends RulesTransformer {
  self: TreeTools with ParseInput  =>

  import c.universe._

  override def transformRuleCalls(tree: c.Tree,
                                   enclosingRule: RuleInfo,
                                   rulesMap: HashMap[String, RuleInfo],
                                   expandedRules: HashMap[String, RuleInfo],
                                   rulesPath: List[String]): c.Tree = {

    def inlineExpand(ruleName: TermName, typeArgs: List[c.Type], args: List[c.Tree]): Option[c.Tree] =
      if (!rulesPath.contains(ruleName.toString)) {
        getValidRuleInfo(ruleName,rulesMap, typeArgs, args).collect[c.Tree] {
          case RuleInfo(typ, code, params, _,_) =>
            val substituted = subsituteParams(params.map(_.symbol), args, code)
            val newRulesPath = ruleName.toString :: rulesPath
            val transformedCode = transformRuleCalls(substituted, enclosingRule, rulesMap, expandedRules, newRulesPath)
            q"compound[${typ}](${transformedCode})"
        }
      }
      else None

    def callParent = super.transformRuleCalls(tree, enclosingRule, rulesMap, expandedRules, rulesPath)

    tree match {
      case q"${ruleName: TermName}[..$t](..$args)" =>
        inlineExpand(ruleName, t.map(_.tpe), args) getOrElse callParent
      case q"${ruleName: TermName}(..$args)" =>
        inlineExpand(ruleName, Nil, args) getOrElse callParent
      case q"${ruleName: TermName}[..$t]" =>
        inlineExpand(ruleName, t.map(_.tpe), Nil) getOrElse callParent
      case q"${ruleName: TermName}" =>
        inlineExpand(ruleName, Nil, Nil) getOrElse callParent
      case _ => callParent
    }
  }
}
