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
    /*val parser = FastParser{
      def int = range('0','9')
      //def num1 = not(int)
      def rule3 = int ~ 'c'
      def blocks = rep(rule3)

     // def rule32 = -('a' ~ 'b') ~ rep('b')
    }  */
    val input =
      """|1
        |00:00:00,004 --> 00:00:03,491
        |<i>Previously on AMC's
        |The Walking Dead...</i>
        |
        |2
        |00:00:04,691 --> 00:00:06,425
        |Beth!
        |Where is Beth!?
        |
      """.stripMargin

    case class SrtTime(h:Int,m:Int,s:Int,ms:Int)
    case class SrtTimeRange(begin:SrtTime,end:SrtTime)
    case class SrtText(range:SrtTimeRange,text:String)

    val srtParser = FastParser{
      def clrf = ('\r' ~ opt('\n')) | '\n'
      def int = rep1(range('0','9')) ^^ {case x:List[Char] => x.mkString.toInt}
      def time = int ~ ignore(':') ~ int ~ ignore(':') ~ int ~ ignore(',') ~ int ^^ {case x:Tuple4[Int,Int,Int,Int] => SrtTime(x._1,x._2,x._3,x._4)}
      def wss = rep(' ')
      def timeRange = time ~ -(wss ~ '-' ~ '-' ~ '>' ~ wss) ~ time ^^ {case x:Tuple2[SrtTime,SrtTime] => SrtTimeRange(x._1,x._2)}
      def anyText = (not(clrf ~ clrf) ~> wildcard[Char]).repFold(""){(acc:String,v:Char) => acc + v}
      def block = -(int ~ clrf) ~ timeRange ~ -clrf ~ anyText ~ -rep(clrf,1,2) ^^ {case x:Tuple2[SrtTimeRange,String] => SrtText(x._1,x._2)}
      def blocks = (rep(block))
    }

    srtParser.blocks(input) match {
      case Success(result) => print(result)
      case Failure(msg) => println("error : " + msg)
    }
  }
}
