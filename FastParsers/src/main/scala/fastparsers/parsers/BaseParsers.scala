package fastparsers.parsers

import fastparsers.input.InputWindow
import InputWindow.InputWindow
import scala.annotation.compileTimeOnly
import scala.util.parsing.input.Positional

/**
 * Interface for Base parsers
 */

trait BaseParsers[Elem, Input] {


  /*
    Used to deconstruct tuples, i.e write case a~b =>  instead of case (a,b) =>
  */
  object ~ {
    def unapply[T, U](x: Tuple2[T, U]): Option[Tuple2[T, U]] = Some((x._1, x._2))
  }

  abstract class ElemOrRange

  @compileTimeOnly("can’t be used outside FastParser")
  implicit def toElemOrRange(elem: Elem): ElemOrRange = ???

  @compileTimeOnly("can’t be used outside FastParser")
  implicit def toElemOrRange(elem: (Elem,Elem)): ElemOrRange = ???




  @compileTimeOnly("toElem can’t be used outside FastParser")
  implicit def toElem(elem: Elem): Parser[Elem] = ???

  @compileTimeOnly("toElem can’t be used outside FastParser")
  implicit def toElem(elem: (Elem, Elem)): Parser[Elem] = ???

  @compileTimeOnly("range can’t be used outside FastParser")
  def range(a: Elem, b: Elem): Parser[Elem] = ???

  @compileTimeOnly("accept can’t be used outside FastParser")
  def accept(p1: ElemOrRange, p2: ElemOrRange*):Parser[Elem] = ???



  /*@compileTimeOnly("can’t be used outside FastParser")
  def acceptRec(p1: ElemOrRange, p2: ElemOrRange*):fastparsers.parsers.Parser[Nothing] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def notRec(p1: ElemOrRange, p2: ElemOrRange*):fastparsers.parsers.Parser[Nothing] = ??? */



  @compileTimeOnly("not can’t be used outside FastParser")
  def not(p1: ElemOrRange, p2: ElemOrRange*): Parser[Elem] = ???

  @compileTimeOnly("acceptIf can’t be used outside FastParser")
  def acceptIf(f: Elem => Boolean): Parser[Elem] = ???

  @compileTimeOnly("wildcard can’t be used outside FastParser")
  def wildcard: Parser[Elem] = ???

  @compileTimeOnly("takeWhile can’t be used outside FastParser")
  def takeWhile(f: Elem => Boolean): Parser[Input] = ???

  @compileTimeOnly("takeWhile can’t be used outside FastParser")
  def takeWhile2(f: Elem => Boolean): Parser[InputWindow[Input]] = ???

  @compileTimeOnly("take can’t be used outside FastParser")
  def take(n: Int): Parser[Input] = ???

  @compileTimeOnly("raw can’t be used outside FastParser")
  def raw[T](p:Parser[T]):Parser[InputWindow[Input]] = ???

  @compileTimeOnly("guard can’t be used outside FastParser")
  def guard[T](p: Parser[T]): Parser[T] = ???

  @compileTimeOnly("phrase can’t be used outside FastParser")
  def phrase[T](p: Parser[T]): Parser[T] = ???

  @compileTimeOnly("failure can’t be used outside FastParser")
  def failure(msg: String): Parser[Any] = ???

  @compileTimeOnly("success can’t be used outside FastParser")
  def success[T](v: T): Parser[T] = ???


  @compileTimeOnly("position can’t be used outside FastParser")
  def position: Parser[Int] = ???

  @compileTimeOnly("positioned can’t be used outside FastParser")
  def positioned[T <: Positional](p: Parser[T]): Parser[T] = ???

  @compileTimeOnly("positioned can’t be used outside FastParser")
  def call[T](p: Any,params: Any*) : Parser[T] = ???
  @compileTimeOnly("positioned can’t be used outside FastParser")
  def callParam[T](p: String) : Parser[T] = ???
  def compound[T](p:Parser[T]): Parser[T] = ???
  def foreignCall[T](p: Any, ruleName: Any, params: Any*) = ???


  @compileTimeOnly("positioned can’t be used outside FastParser")
  def paramRule[T](p: Any, params: Any*): Parser[T] = ???

  trait BaseParser[T] {
    @compileTimeOnly("~ can’t be used outside FastParser")
    def ~[U](p2: Parser[U]): Parser[(T, U)] = ???

    @compileTimeOnly("~> can’t be used outside FastParser")
    def ~>[U](p2: Parser[U]): Parser[U] = ???

    @compileTimeOnly("<~ can’t be used outside FastParser")
    def <~[U](p2: Parser[U]): Parser[T] = ???

    @compileTimeOnly("|| can’t be used outside FastParser")
    def ||[U >: T](p2: Parser[U]): Parser[U] = ???

    @compileTimeOnly("| can’t be used outside FastParser")
    def |[U >: T](p2: Parser[U]): Parser[U] = ???

    @compileTimeOnly("^^ can’t be used outside FastParser")
    def ^^[U](f: T => U): Parser[U] = ???

    @compileTimeOnly("map can’t be used outside FastParser")
    def map[U](f: T => U): Parser[U] = ???

    @compileTimeOnly("^^^ can’t be used outside FastParser")
    def ^^^[U](f: U): Parser[U] = ???

    @compileTimeOnly("filter can’t be used outside FastParser")
    def filter[U >: T](f: T => Boolean): Parser[T] = ???

    @compileTimeOnly("withFailureMessage can’t be used outside FastParser")
    def withFailureMessage(msg: String): Parser[T] = ???
  }


  implicit class elemParser(p1: Elem) extends BaseParser[Elem]
  implicit class baseParsers[T](p1: Parser[T]) extends BaseParser[T]

}
