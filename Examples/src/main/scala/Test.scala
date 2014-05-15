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

//GROS HACK
import fastparsers.input.InputWindow.InputWindow

sealed abstract class JSValue
case class JSObject(map: List[(InputWindow[Array[Char]], JSValue)]) extends JSValue
case class JSArray(arr: List[JSValue]) extends JSValue {
  override def equals(obj: Any) = obj match {
    case tmp: JSArray => arr.toSet == tmp.arr.toSet
    case _ => false
  }
}

//case class JSInt(i: Int) extends JSValue
case class JSDouble(d: InputWindow[Array[Char]]) extends JSValue
case class JSDouble2(d: Double) extends JSValue
case class JSString(s: InputWindow[Array[Char]]) extends JSValue
case class JSBool(b: Boolean) extends JSValue
case object JSNull extends JSValue
object JTrue extends JSValue
object JFalse extends JSValue


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
     val jsonparser = /*getAST.get(*/FastParsersCharArray  {
       def value:Parser[JSValue] = whitespaces ~>
        (
          obj |
          arr |
          stringLit ^^ {x => JSString(x)} |
          decimalNumber ^^ {x => JSDouble(x)} |
          lit(nullValue) ^^^ JSNull |
          lit(trueValue) ^^^ JSBool(true) |
          lit(falseValue) ^^^ JSBool(false)
        )

       def obj:Parser[JSValue] = ('{' ~> repsep(member,comma) <~ closeBracket) ^^ {x => JSObject(x)}
       def arr:Parser[JSValue] = ('[' ~> repsep(value,comma) <~ closeSBracket) ^^ {x => JSArray(x)}
       def member:Parser[(InputWindow[Array[Char]], JSValue)] = stringLit ~ (lit(points) ~> value)
     }//)
   }
	
	object CSV{
		import fastparsers.framework.implementations.FastParsersCharArray._
		//GROS HACK
		import fastparsers.input.InputWindow.InputWindow
		 val trueValue = "true".toCharArray
		 val falseValue = "false".toCharArray
		 val close = "]".toCharArray
		 val comma = ",".toCharArray
		 
		val cvsParser = (FastParsersCharArray  {
			def primBools = ('t' ~ 'r' ~ 'u' ~ 'e' ~> success(JTrue)) | ('f' ~ 'a' ~ 'l' ~'s' ~ 'e' ~> success(JFalse)) 
			def bools = '[' ~> repsep(primBools, ',') <~ close// ^^ (x => JSArray(x))
		})
	}
	
  def hey(x: Any): Unit = x match {
    case y :: ys => hey(y)
    case y : InputWindow.CharArrayStruct => println("hey")
    case (a, b) => hey(a)
    case _ =>
  }

  val bigFileName = "FastParsers/src/test/resources/" + "csvBooleans.txt"
  val bigFile = scala.io.Source.fromFile(bigFileName).getLines mkString "\n"
  val bigFileArray = bigFile.toCharArray

  /*println("hey, wait a bit")

  Thread.sleep(5000)*/
	
	//println(LMSCSVBooleanParseGen.apply(bigFileArray))
	//println(CSVBoolHandWritten.apply(bigFileArray))

  CSV.cvsParser.bools(bigFileArray) match {
    case Success(x) =>
      println("hey2")
      println(x)
    case Failure(msg) => println("failure: " + msg)
  }

  //LMSJsonParserGen2.apply(bigFileArray)
 }
}