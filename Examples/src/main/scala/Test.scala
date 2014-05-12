/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

//because warnings

import fastparsers.framework.getAST
import fastparsers.framework.implementations.{FastParsers, FastArrayParsers}
import fastparsers.framework.parseresult._
import fastparsers.input.InputWindow
import fastparsers.parsers.Parser
import scala.collection.mutable.HashMap
import scala.language.reflectiveCalls
import scala.language.implicitConversions
import scala.reflect.ClassTag


object Test {

 def main(args: Array[String])  {

   object JSonImpl2 {
     import fastparsers.framework.implementations.FastParsersCharArray._
     val nullValue = "null".toCharArray
     val trueValue = "true".toCharArray
     val falseValue = "false".toCharArray
     val closeBracket = "}".toCharArray
     val closeSBracket = "]".toCharArray
     val comma = ",".toCharArray
     val points = ":".toCharArray
     val jsonparser = getAST.get(FastParsersCharArray  {
       def value:Parser[Any] = whitespaces ~> (obj | arr | stringLit | decimalNumber | nullValue | trueValue | falseValue)
       def obj:Parser[Any] = '{' ~> repsep(member,comma) <~ closeBracket
       def arr:Parser[Any] = '[' ~> repsep(value,comma) <~ closeSBracket
       def member:Parser[Any] = stringLit ~ (lit(points) ~> value)
     })
   }

  def hey(x: Any): Unit = x match {
    case y :: ys => hey(y)
    case y : InputWindow.CharArrayStruct => println("heysd")
    case (a, b) => hey(a)
    case _ =>
  }


  val bigFileName = "FastParsers/src/test/resources/" + "json.big1"
  val bigFile = scala.io.Source.fromFile(bigFileName).getLines mkString "\n"
  val bigFileArray = bigFile.toCharArray
  println("hey")
  JSonImpl2.jsonparser.value(bigFileArray) match {
    case Success(x) =>
      println("hey")
      println(x)
    //  hey(x)
    case Failure(msg) => println("failure: " + msg)
  }  */
   val nb = 50
   val start = 20
   val size = 10000

   {
     var average = 0.0
     (0 to nb).foreach { i =>
       var pos = 0
       var x = 0
       var lit = ""

       val now = System.nanoTime
       //val array = new Array[String](size)
       while (pos < size){
         x = ((y : Int) => y + 1)(x)
         pos += 1
         lit = pos.toString
       }
       val micros = (System.nanoTime - now) /1e6
       if (i >= start)
         average += micros
     }
     println(average / (nb - start))
   }
   {
     var average = 0.0
     (0 to nb).foreach { i =>
       var pos = 0
       var x = 0
       var lit = ""

       val now = System.nanoTime
       //val array = new Array[String](size)
      val tmp = ((y : Int) => y + 1)
       while (pos < size){
         x = tmp(x)
         pos += 1
         lit = pos.toString
       }
       val micros = (System.nanoTime - now) /1e6
       if (i >= start)
         average += micros
     }
     println(average / (nb - start))
   }
 }
}