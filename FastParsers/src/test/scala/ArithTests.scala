/**
 * Created by Eric on 15.03.14.
 */
import org.scalatest._
import FastParsers._
import StreamMarked._
import UnitTests._

class ArithTests extends FunSuite {
  def termCalc(b:Any):Int = b match {case (x:Int,fs:List[((Int,Int) => Int,Int)]) =>  fs.foldLeft(x){(acc,elem) => elem._1(acc,elem._2)}}
  // def termCalc(b:(Int,List[(Int,Int) => Int])):Int = b match { case (x,elem) =>  fs.foldLeft(x){(acc,elem) => elem._1(acc,elem._2)}

  val calc = FastParser{
    def int = rep1(range('0','9')) map {case x:List[Char] => x.mkString.toInt}
    def op1 = '*' ^^^ {(y:Int,x:Int) => y * x} |
              '/' ^^^ {(y:Int,x:Int) => y / x}
    def op2 = '+' ^^^ {(y:Int,x:Int) => y + x} |
              '-' ^^^ {(y:Int,x:Int) => y - x}

    def wss = -rep(' ')
    def factor = int | parens
    def parens:Parser[Int] =  '(' ~> expr <~ ')'
    def term = factor ~ rep(wss ~ op1 ~ wss ~ factor) ^^ termCalc
    def expr:Parser[Int] = term ~ rep(wss ~ op2 ~ wss ~ term) ^^ termCalc
    def start = phrase(expr)
  }

  test("Arithmetic") {
    shouldSucced(calc.start)(
      "2+2" gives 4,
      "(5*6)/3" gives 10,
      "5 + 2*3 + 9" gives 20,
      "(5 + 2)* (3   +9)" gives 84

    )
    shouldFail(calc.start)(
      "bbacb"
    )
  }
}
