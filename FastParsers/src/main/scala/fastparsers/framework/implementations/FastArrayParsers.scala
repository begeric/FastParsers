package fastparsers.framework.implementations

import scala.language.experimental.macros
import fastparsers.parsers.{FlatMapParsers, RepParsers, BaseParsers}

/**
 * Created by Eric on 22.04.14.
 * Interface for ArrayParserImpl
 */
class FastArrayParsers[T] extends BaseParsers[T, Array[T]] with RepParsers with FlatMapParsers {
  def apply(rules: => Unit): Any = macro ArrayParserImpl.ArrayParserImpl[T]
}
