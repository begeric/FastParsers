/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
import FastParsers._
import StreamMarked._

import scala.util.parsing.input._
object Test {
  implicit def stringToCharSeqReader(s:String) = new CharSequenceReader(s)
  implicit def stringToStreamMarkedArray(s:String) = new StreamMarkedArray(s.toCharArray)


  def anyToCharList(a:Any):List[Char] = a match {
    case (x:Char,y:List[Char]) => x::y
  }

  //val x = {case (x:Char,y:List[Char]) => x::y}.apply('a',Nil)
  //val a = ((y:Any) => y match {case (x:Char,y:List[Char]) => x :: y})('a',Nil)

  def main(args: Array[String]) {
    val parser = FastParser{
      def rule1 = 'b' ~ 'a' ~ rule2// ~ 'b' ~ 'l'
      def rule2 = 'c' ~ 'b'

      def rule3 = ('a' ~ 'b').rep(2,3) ~ 'c'
      def rule4 = ('a' ~ 'b').+ ~ 'c'
      def rule5 = ('a' ~ 'b').* ~ 'c'
      def rule6 = ('a' ~ 'b').? ~ 'c'

      //def rule7 = rule1
      def rule8 = (rep('b',0,-1) ~ ('a' || 'c')) ^^ {case (x:List[_],y) => println(x.size);y}

      def rule9 = rep1(range('0','9'))

      def rule10 = range('0','9') ~> rep(range('a','z')) <~ '0'

      def rule11 = phrase('a' ~ 'b' ~ 'c')

      def rule12 = guard('a' ~ 'b' ~ 'c') ~ rep(range('a','z'))
      def rule13 = not('a' ~ 'b' ~ 'c') ~ rep(range('a','z'))

      def rule14 = rule1 ~ ('c' || 'd')

      def rule15 = 'a' ~ 'b' ~ 'c' ^^^ 1

      def rule16 = 'a' ~ ('b' ^^^ 2) ~ 'c'
      def rule17 = 'a' ~ ('b' ^^ {_ => 2}) ~ 'c'

      def rule18 = phrase(rep('a',2,2) ~ 'b')
      def rule19 = phrase(rep('a',0,3)) | rep('a',0,4)
      def rule20 = (rep('a',0,3) ~ 'b') | rep('a' || 'b')

      def rule21 = ((rep(range('a','z'))) filter {case x:List[_] => x.mkString == "salut" || x.mkString == "hello"})

      def rule22 = repFold(range('0','9'))(0){(y:Int,x:Any) => x.asInstanceOf[Char].asDigit + 1 +y}
      def rule23 = range('0','9').repFold(1){(y:Int,x:Char) => x.asDigit * y}

      def rule24 = phrase(rep(range('a','z') || '?'))

      //def rule25:Parser[List[Char]] = ('a' ~ rule26) ^^ {case x:Tuple2[Char,List[Char]] => x._1 :: x._2}  //this is retarded
      def rule25:Parser[List[Char]] = ('a' ~ rule26) ^^  {case x:Tuple2[Char,List[Char]] => List(x._1) ++ x._2}  //this is retarded
      def rule26:Parser[List[Char]] = ('b' ^^ {case x:Char => List(x)}) || rule25

     /* def rule27 = rep('a' ~ 'b') ~ rep('a')
      def rule28 = rep('a') ~ 'b'
      /*def rule29 = seq(List('s','a','l','u','t'))
      def rule30 = seq("salut")   */

      def rule31 = 'a' ~ ('b' withFailureMessage("JE VEUX UN 'b' ICI")) ~ 'c'
                */
      def rule32 = phrase(rule25)


     /* def rule33 = 'a' ~ 'b' | 'c' ~ 'd'
      def rule34 = (rep('a') ~ 'b') | 'c' ^^ {case x:Char => (List(x),x)}
      def rule26:Parser[List[Char]] = rep('a')
      def rule25 = ('a' ~ rule26) ^^ {x => List('a')}  //this is retarded       */
    }

    parser.rule3("abbb") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error 32: " + msg)
    }
  }
}
