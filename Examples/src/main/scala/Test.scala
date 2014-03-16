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


  def main(args: Array[String]) {

    val parser = FastParser{
      def rule1 = 'a' ~ alt("abc".toList)
    }

    /*val httpResponParser = FastParser{

    }*/

    parser.rule1("ac") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }
  }
}
