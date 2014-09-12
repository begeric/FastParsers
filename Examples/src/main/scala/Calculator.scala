/**
 * Created by Eric on 07.04.14.
 */

import fastparsers.framework.implementations.FastParsers
import fastparsers.framework.parseresult._
import fastparsers.parsers.Parser
import scala.language.reflectiveCalls
import scala.language.implicitConversions

object Calculator {

  def main(args: Array[String])  {
    import FastParsers._

    def exec(x:(Int,Option[(Function2[Int,Int,Int],Int)])):Int = x._2 match {
      case None => x._1
      case Some((op,y)) => op(x._1,y)
    }

    val code = FastParser{
      def op1 = lit("+") ^^^ ((x:Int,y:Int) => x + y) |
                lit("-") ^^^ ((x:Int,y:Int) => x - y)

      def op2 = lit("*") ^^^ ((x:Int,y:Int) => x * y) |
                lit("/") ^^^ ((x:Int,y:Int) => x / y)

      def factor: Parser[Int] = number ^^(_.toString.toInt) | lit("(") ~> expr <~ ")"
      def term: Parser[Int] = factor ~ opt(op2 ~ term) ^^ exec
      def expr: Parser[Int] = term ~ opt(op1 ~ expr) ^^ exec
    }

    code.expr("21+(5+1 ) * 2 +2 *8") match {
      case Success(x) => println(x)
      case Failure(error) => println("failure : " + error)
    }

  } 
}
