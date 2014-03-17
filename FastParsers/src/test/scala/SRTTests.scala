/**
 * Created by Eric on 15.03.14.
 */
import org.scalatest._
import FastParsers._
import StreamMarked._
import UnitTests._

class SRTTests extends FunSuite {

  def testFileParsing(fileName:String, parser:String => ParseResult[Any]){


    val lines = (scala.io.Source.fromFile(fileName).getLines mkString "\n").substring(3)  //because bugs
    parser(lines) match {
      case Success(result:List[SrtText]) =>
        compare(result.mkString("\n\n"),lines)
      case Failure(msg) => fail("error : " + msg)
    }
  }

  case class SrtTime(h:Int,m:Int,s:Int,ms:Int) {
    override def toString = f"$h%02d" + ":" + f"$m%02d" + ":" + f"$s%02d" + "," + f"$ms%03d"
  }
  case class SrtTimeRange(begin:SrtTime,end:SrtTime) {
    override def toString = begin + " --> " + end
  }
  case class SrtText(id:Int,range:SrtTimeRange,text:String) {
    override def toString = id + "\n" + range + "\n" + text
  }

  val srtParser = FastParser{
    def clrf = ('\r' ~ opt('\n')) | '\n'
    def int = rep1(range('0','9')) ^^ {case x:List[Char] => x.mkString.toInt}
    def time = int ~ FastParsers.ignore(':') ~ int ~ FastParsers.ignore(':') ~ int ~ FastParsers.ignore(',') ~ int ^^ {case x:Tuple4[Int,Int,Int,Int] => SrtTime(x._1,x._2,x._3,x._4)}
    def wss = rep(' ')
    def timeRange = time ~ -(wss ~ '-' ~ '-' ~ '>' ~ wss) ~ time ^^ {case x:Tuple2[SrtTime,SrtTime] => SrtTimeRange(x._1,x._2)}
    def anyText = (not(clrf ~ clrf) ~> wildcard[Char]).repFold[String]("",(acc:String,v:Char) => acc + v)
    def block = int ~ -clrf ~ timeRange ~ -clrf ~ anyText ~ -rep(clrf,0,2) ^^ {case x:Tuple3[Int,SrtTimeRange,String] => SrtText(x._1,x._2,x._3)}
    def blocks = (rep(block))
  }
  test("SRT test : twd413.srt") {
    testFileParsing("twd413.srt",srtParser.blocks)
  }
}
