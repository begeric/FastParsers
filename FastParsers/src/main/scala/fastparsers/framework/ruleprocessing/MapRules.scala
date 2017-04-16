package fastparsers.framework.ruleprocessing

import scala.collection.mutable.HashMap

/**
 * Transform rules
 */
trait MapRules extends RulesProcessing {
  def process(rules: HashMap[String, RuleInfo]): HashMap[String, RuleInfo] = rules
}
