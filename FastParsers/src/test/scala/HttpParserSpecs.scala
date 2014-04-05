import org.scalatest.FunSuite

import HttpParsers._
import scala.util.parsing.combinator._
/**
 * Created by Eric on 05.04.14.
 */
class HttpParserSpecs extends FunSuite  {
  def compareImplementations(fileName: String) {
    test("httpparser : " + fileName){
      val file = (scala.io.Source.fromFile(fileName).getLines mkString "\n")

      def getFastParserResult = httpparser.respAndMessage(file) match {
        case Success(result) =>  result
        case Failure(msg) => fail("error : " + msg)
      }

      import HTTP._
      def getCombinatorResult = HTTP.parse(HTTP.respAndMessage, file) match {
        case HTTP.Success(result,_) => result
        case HTTP.Failure(msg,_) => fail("error : " + msg)
      }

      val now = System.nanoTime
      val res1 = getFastParserResult
      val micros = (System.nanoTime - now) /1e6
      val now2 = System.nanoTime
      val res2 = getCombinatorResult
      val micros2 = (System.nanoTime - now2) /1e6
      assert(res1 == res2)
      //assert(micros < micros2)
      //println(fileName.split('\\').last + " : FastParserResult @ " + micros + " : " + "CombinatorResult @ " + micros2)
      println(fileName.split('\\').last + "  (" + (file.length/1024) + "kb)" + " FastParsers is " + ((micros2 / micros)*100).toInt/100.0 + " times faster than Combinator")
    }
  }

  (1 to 101).foreach{ i =>
    compareImplementations("FastParsers\\src\\test\\resources\\tweet" + i)
  }

}
