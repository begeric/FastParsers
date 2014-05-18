package fastparsers.framework.implementations

import scala.language.experimental.macros
import fastparsers.parsers._
import scala.reflect.macros.whitebox.Context
import fastparsers.framework.ruleprocessing.{RuleCombiner, ParseRules, RulesInliner, RulesTransformer}
import fastparsers.input.ArrayInput
import fastparsers.error.DefaultParseError

/**
 * Created by Eric on 22.04.14.
 * Interface for ArrayParserImpl
 */
class FastArrayParsers[T] extends BaseParsers[T, Array[T]] with RepParsers with FlatMapParsers {
  def apply(rules: => Unit): FinalFastParserImpl = macro ArrayParserImpl.ArrayParserImpl[T]
}

/**
 * An implementation of Parser combinator that deal with Array[T] inputs
 */
object ArrayParserImpl {
  def ArrayParserImpl[T: context.WeakTypeTag](context: Context)(rules: context.Tree): context.Tree =  {
    new BaseImpl with RulesTransformer with RulesInliner
      with ParseRules with BaseParsersImpl with RepParsersImpl
      with FlatMapImpl with RuleCombiner
      with ArrayInput with DefaultParseError with IgnoreResults {

      val c: context.type = context

      import c.universe._

      def inputElemType = c.typecheck(tq"${implicitly[c.WeakTypeTag[T]]}",c.TYPEmode).tpe

    }.FastParser(rules)
  }
}
