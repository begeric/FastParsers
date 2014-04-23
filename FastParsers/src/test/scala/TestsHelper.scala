import fastparsers.framework.implementations.FastParsers
import fastparsers.framework.parseresult.{ParseResult, Success, Failure}
import fastparsers.framework.parseresult.Success
import org.scalatest.{FunSuite, FunSpec}
import scala.util.parsing.combinator.Parsers

/**
 * Created by Eric on 15.03.14.
 */
object TestsHelper extends FunSuite {
  import org.scalatest._
  import FastParsers._


  implicit def strToInput(s:String) = new Input(s)

  class Input(in:String){
    def gives(result:Any) = InputAndResult(in,result)
  }

  case class InputAndResult(in:String, res:Any)

  def shouldSucced[T](rule:(String,Int) => ParseResult[Any,_])(tests:InputAndResult*) = {
    tests.foreach{
      x => rule(x.in,0) match {
        case Success(result) => assert(result == x.res, "on " + x.in + " expected " + x.res + " got " + result)
        case Failure(msg) => fail("Didn't succeed : " + msg + " on \"" + x.in+"\"")
      }
    }
  }

  def shouldFail(rule:(String,Int) => ParseResult[Any,_])(tests:String*) = {
    tests.foreach{
      str => rule(str,0) match {
        case Success(result) => fail("Wasn't supposed to succeed, result : " + result)
        case _ =>
      }
    }
  }

  def compare(s1:String, s2:String) = {
    s1.zip(s2).zipWithIndex.foreach{case ((a1,a2),i) =>
      if (a1 != a2)
        fail("error at " + i + " : " + s1.substring(i,i + 10) + " != " + s2.substring(i,i + 10))
    }
  }

  def repeat[T](x:T,n:Int):List[T] = n match {
    case a if a <= 0 => Nil
    case _ => x::repeat(x,n - 1)
  }



  def compareImplementations(fileName: String, fast:(String,Int) => Any,combHelper:Parsers, comb:CharSequence => Any) {
      val file = scala.io.Source.fromFile(fileName).getLines mkString "\n"
      val fileArray = file.toCharArray
      val charSeq = new FastCharSequence(fileArray)

      def getFastParserResult = fast(file,0) match {
        case Success(result) =>  result
        case Failure(msg) => fail("error : " + msg)
      }

      def getCombinatorResult = comb(charSeq) match {
        case combHelper.Success(result,_) => result
        case combHelper.Failure(msg,_) => fail("error : " + msg)
      }

      val now = System.nanoTime
      val res1 = getFastParserResult
      val micros = (System.nanoTime - now) /1e6
      val now2 = System.nanoTime
      val res2 = getCombinatorResult
      val micros2 = (System.nanoTime - now2) /1e6
      //assert(res1 == res2)
      //println(res1 + " : " + res2)
      //assert(micros < micros2)
      //println(fileName.split('\\').last + " : FastParserResult @ " + micros + " : " + "CombinatorResult @ " + micros2)
      println(fileName.split('/').last + "  (" + (file.length/1024) + "kb)" + " FastParsers is " + ((micros2 / micros)*100).toInt/100.0 + " times faster than Combinator")
    }

}