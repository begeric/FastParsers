/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

//because warnings

import fastparsers.framework.getAST
import fastparsers.framework.implementations.FastArrayParsers
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
     val jsonparser = FastParsersCharArray{
       def value:Parser[Any] = whitespaces ~> (obj | arr | stringLit | decimalNumber | "null".toCharArray | "true".toCharArray | "false".toCharArray)
       def obj:Parser[Any] = '{' ~> repsep(member,",".toCharArray) <~ "}".toCharArray
       def arr:Parser[Any] = '[' ~> repsep(value,",".toCharArray) <~ "]".toCharArray
       def member:Parser[Any] = stringLit ~ (lit(":".toCharArray) ~> value)
     }
   }

   def hey(x: Any): Unit = x match {
     case y :: ys => hey(y)
     case y : InputWindow.CharArrayStruct => println("hey")
     case (a, b) => hey(a)
     case _ =>
   }

   val bigFileName = "FastParsers/src/test/resources/" + "json.big1"
   val bigFile = scala.io.Source.fromFile(bigFileName).getLines mkString "\n"
   val bigFileArray = bigFile.toCharArray
   println("hey")
   JSonImpl2.jsonparser.value(bigFileArray) match {
     case Success(x) =>
       println("hey2")
       println(x)
       hey(x)
     case Failure(msg) => println("failure: " + msg)
   }
 }
}