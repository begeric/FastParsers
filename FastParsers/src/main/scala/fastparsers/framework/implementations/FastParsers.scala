package fastparsers.framework.implementations

import scala.language.experimental.macros
import fastparsers.parsers._
import scala.reflect.macros.whitebox.Context
import fastparsers.framework.ruleprocessing.{RuleCombiner, ParseRules, RulesInliner, RulesTransformer}
import fastparsers.input.StringInput
import fastparsers.error.IgnoreParseError

/**
 * Example of a parser working on string.
 * Interface for FastParsersImpl
 */
object FastParsers extends BaseParsers[Char, String] with RepParsers with TokenParsers[String] with FlatMapParsers {
  def FastParser(rules: => Unit): FinalFastParserImpl = macro FastParsersImpl.FastParser
}

/**
 * Here is where the FastParsers implementation is composed to make an actual useful FastParsers
 */

class FastParsersImpl(val c: Context) extends BaseImpl
  with RulesTransformer with RulesInliner
  with ParseRules with BaseParsersImpl with RepParsersImpl
  with TokenParsersImpl with FlatMapImpl with RuleCombiner
  with StringInput with IgnoreParseError  with DontIgnoreResults{

  override def FastParser(rules: c.Tree) = super.FastParser(rules) //why ??
}
