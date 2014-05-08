package fastparsers.framework.implementations

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context
import fastparsers.parsers.{TokenParsersImpl, FlatMapImpl, RepParsersImpl, BaseParsersImpl}
import fastparsers.input.CharArrayInput
import fastparsers.framework.ruleprocessing.{RulesInliner, RuleCombiner, ParseRules, RulesTransformer}
import fastparsers.error.{IgnoreParseError, DefaultParseError}

/**
 * Created by Eric on 22.04.14.
 * Implementation of Parsers that deal with CharArray (different from ArrayParser[Char] in that it can deal with
 * TokenParsers
 */
class CharArrayImpl(val c: Context) extends FastParsersImpl with RulesTransformer with RulesInliner
  with ParseRules with BaseParsersImpl with RepParsersImpl with FlatMapImpl with RuleCombiner
  with TokenParsersImpl with CharArrayInput with IgnoreParseError {
  override def FastParser(rules: c.Tree) = super.FastParser(rules) //why ??
}
