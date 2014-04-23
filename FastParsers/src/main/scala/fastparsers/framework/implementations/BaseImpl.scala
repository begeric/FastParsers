package fastparsers.framework.implementations

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context
import fastparsers.parsers.{FlatMapImpl, TokenParsersImpl, RepParsersImpl, BaseParsersImpl}
import fastparsers.input.StringInput
import fastparsers.framework.ruleprocessing.{RulesInliner, RuleCombiner, ParseRules, RulesTransformer}

/**
 * Here is where the FastParsers implementation is composed to make an actual useful FastParsers
 */

class BaseImpl(val c: Context) extends FastParsersImpl with RulesTransformer with RulesInliner
            with ParseRules with BaseParsersImpl with RepParsersImpl
            with TokenParsersImpl with FlatMapImpl with RuleCombiner
            with StringInput {

  override def FastParser(rules: c.Tree) = super.FastParser(rules) //why ??
}
