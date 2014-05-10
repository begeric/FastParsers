package fastparsers.parsers

import scala.annotation.compileTimeOnly

/**
 * Created by Eric on 22.04.14.
 * Interface for parsers representing repetition
 */
trait RepParsers {

  @compileTimeOnly("can’t be used outside FastParser")
  def rep[T](p: Parser[T], min: Int = 0, max: Int = -1): Parser[List[T]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def rep1[T](p: Parser[T]): Parser[List[T]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def repN[T](p: Parser[T], rep: Int): Parser[List[T]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def opt[T](p: Parser[T]): Parser[Option[T]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def repsep[T, U](p: Parser[T], sep: Parser[U]): Parser[List[T]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def repsep1[T, U](p: Parser[T], sep: Parser[U]): Parser[List[T]] = ???


  @compileTimeOnly("can’t be used outside FastParser")
  def until[T,U](p: Parser[T], sep: Parser[U]): Parser[List[T]] = ???

  implicit class repParser[T](p: Parser[T]) {
    @compileTimeOnly("can’t be used outside FastParser")
    def foldLeft[U](init: U, f: (U, T) => U): Parser[U] = ???

    @compileTimeOnly("can’t be used outside FastParser")
    def foldRight[U, X >: T](init: U, f: (T, U) => U): Parser[U] = ???

    @compileTimeOnly("can’t be used outside FastParser")
    def reduceLeft[U >: T](f: (U, T) => U): Parser[U] = ???

    @compileTimeOnly("can’t be used outside FastParser")
    def reduceRight[U >: T](f: (T, U) => U): Parser[U] = ???

  }

}
