package fastparsers.framework.implementations

import scala.language.experimental.macros
import fastparsers.parsers.{FlatMapParsers, TokenParsers, RepParsers, BaseParsers}

/**
 * Interface for CharArrayImpl
 */
object FastParsersCharArray extends BaseParsers[Char, Array[Char]] with RepParsers with TokenParsers[Array[Char]] with FlatMapParsers {
  def FastParsersCharArray(rules: => Unit): Any = macro CharArrayImpl.FastParser
}
