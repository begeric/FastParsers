package fastparsers.framework.implementations

import scala.language.experimental.macros
import fastparsers.parsers.{FlatMapParsers, TokenParsers, RepParsers, BaseParsers}

/**
 * Example of a parser working on string.
 */
object FastParsers extends BaseParsers[Char, String] with RepParsers with TokenParsers[String] with FlatMapParsers {
  def FastParser(rules: => Unit): Any = macro BaseImpl.FastParser
}
