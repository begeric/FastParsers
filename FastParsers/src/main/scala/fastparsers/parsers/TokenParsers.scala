package fastparsers.parsers

import fastparsers.input.InputWindow
import InputWindow.InputWindow
import scala.annotation.compileTimeOnly

/**
 * Created by Eric on 22.04.14.
 */
trait TokenParsers[StringRepr] {

  @compileTimeOnly("can’t be used outside FastParser")
  implicit def lit(str: StringRepr): Parser[StringRepr] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def ident: Parser[InputWindow[StringRepr]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def number: Parser[InputWindow[StringRepr]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def decimalNumber: Parser[InputWindow[StringRepr]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def stringLit: Parser[InputWindow[StringRepr]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def whitespaces: Parser[InputWindow[StringRepr]] = ???

}
