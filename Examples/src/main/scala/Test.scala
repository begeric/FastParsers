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
      def int = range('0','9')
      //def num1 = not(int)
      def rule3 = int ~ 'c'
      def blocks = rep(rule3)
     // def rule32 = -('a' ~ 'b') ~ rep('b')
    }
    val input =
      """
        1
        00:00:00,004 --> 00:00:03,491
        <i>Previously on AMC's
        The Walking Dead...</i>

        2
        00:00:04,691 --> 00:00:06,425
        Beth!
        Where is Beth!?

      """.stripMargin

    case class srtTime(h:Int,m:Int,s:Int,ms:Int)
    type srtTimeRange = (srtTime,srtTime)
    case class srtText(range:srtTimeRange,text:String)


    val srtParser = FastParser{
      def clrf = ('\r' ~ opt('\n')) | '\n'
      def int = range('0','9')
      def num1 = rep1(int)
      def num2 = rep(int,2,2)
      def num3 = rep(int,3,3)
      def time = num2 ~ ignore(':') ~ num2 ~ ignore(':') ~ num2 ~ ignore(',') ~ num3
      def wss = rep(' ')
      def timeRange = time ~ -(wss ~ '-' ~ '-' ~ '>' ~ wss) ~ time
      def anyText = rep(wildcard[Char])
      def block = num1 ~ -clrf ~ timeRange ~ -clrf ~ anyText ~ rep(clrf,2,2)
      def blocks = phrase(rep(block))
    }

    parser.blocks("1c2c3c") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error 32: " + msg)
    }
  }
}
