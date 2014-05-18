package fastparsers.framework.implementations

import scala.language.experimental.macros
import fastparsers.parsers._
import scala.reflect.macros.whitebox.Context
import fastparsers.framework.ruleprocessing.{RuleCombiner, ParseRules, RulesInliner, RulesTransformer}
import fastparsers.input.CharArrayInput
import fastparsers.error.IgnoreParseError

/**
 * Interface for CharArrayImpl
 */
object FastParsersCharArray extends BaseParsers[Char, Array[Char]] with RepParsers with TokenParsers[Array[Char]] with FlatMapParsers {
  def FastParsersCharArray(rules: => Unit): FinalFastParserImpl = macro CharArrayImpl.FastParser
}

/**
 * Implementation of Parsers that deal with CharArray (different from ArrayParser[Char] in that it can deal with
 * TokenParsers
 */
class CharArrayImpl(val c: Context) extends BaseImpl with RulesTransformer with RulesInliner
  with ParseRules with BaseParsersImpl with RepParsersImpl with FlatMapImpl with RuleCombiner
  with TokenParsersImpl with CharArrayInput
  with IgnoreParseError  with IgnoreResults {
  override def FastParser(rules: c.Tree) = super.FastParser(rules) //why ??
}
