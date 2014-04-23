/**
 * Created by Eric on 07.04.14.
 */
object Calculator {

  /*def main(args: Array[String])  {
    import FastParsers._

    def exec(x:(Int,Option[(Function2[Int,Int,Int],Int)])):Int = x._2 match {
      case None => x._1
      case Some((op,y)) => op(x._1,y)
    }

    val parser = FastParser{
      def op1 = lit("+") ^^^ ((x:Int,y:Int) => x + y) |
                lit("-") ^^^ ((x:Int,y:Int) => x - y)

      def op2 = lit("*") ^^^ ((x:Int,y:Int) => x * y) |
                lit("/") ^^^ ((x:Int,y:Int) => x / y)

      def factor:fastparsers.parsers.Parser[Int] = number ^^(_.toString.toInt) | lit("(") ~> expr <~ ")"
      def term:fastparsers.parsers.Parser[Int] = factor ~ opt(op2 ~ term) ^^ exec
      def expr:fastparsers.parsers.Parser[Int] = term ~ opt(op1 ~ expr) ^^ exec
    }

    parser.expr("21+(5+1 ) * 2 +2 *8") match {
      case Success(x) => println(x)
      case Failure(error) => println("failure : " + error)
    }

  } */
}
