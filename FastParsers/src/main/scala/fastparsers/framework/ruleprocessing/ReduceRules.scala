package fastparsers.framework.ruleprocessing

import scala.collection.mutable.HashMap

/**
 * Combine rules
 */
trait ReduceRules extends RulesProcessing {
  def combine(rules: HashMap[String, RuleInfo]): c.Tree
}
