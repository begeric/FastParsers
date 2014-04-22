package fastparsers.framework.implementations

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context
import fastparsers.framework.ruleprocessing._
import fastparsers.parsers.{FlatMapImpl, RepParsersImpl, BaseParsersImpl}
import fastparsers.input.ArrayInput
import fastparsers.framework.ruleprocessing.{InlineRules, RuleCombiner, ParseRules}

/**
 * Created by Eric on 22.04.14.
 */
object ArrayParserImpl {
  def ArrayParserImpl[T: context.WeakTypeTag](context: Context)(rules: context.Tree): context.Tree =  {
    new FastParsersImpl with InlineRules
      with ParseRules with BaseParsersImpl with RepParsersImpl
      with FlatMapImpl with RuleCombiner with ArrayInput {

      type Elem = T
      type Input = Array[Elem]
      val c: context.type = context
      val typ = implicitly[c.WeakTypeTag[T]]

    }.FastParser(rules)
  }
}
