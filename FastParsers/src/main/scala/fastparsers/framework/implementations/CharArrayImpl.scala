package fastparsers.framework.implementations

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context
import fastparsers.parsers.{TokenParsersImpl, FlatMapImpl, RepParsersImpl, BaseParsersImpl}
import fastparsers.input.CharArrayInput
import fastparsers.framework.ruleprocessing.{RuleCombiner, ParseRules, InlineRules}

/**
 * Created by Eric on 22.04.14.
 */
class CharArrayImpl(val c: Context) extends FastParsersImpl with InlineRules
  with ParseRules with BaseParsersImpl with RepParsersImpl with FlatMapImpl with RuleCombiner
  with TokenParsersImpl with CharArrayInput {
  override def FastParser(rules: c.Tree) = super.FastParser(rules) //why ??
}
