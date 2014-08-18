import fastparsers.input.InputWindow._
import scala.util.parsing.combinator._
import scala.util.parsing.input._
import scala.annotation.compileTimeOnly

/**
 * Created by Eric on 05.04.14.
 */
object CSVParsers {
  
    sealed abstract class JSValue
    case class JSArray(arr: List[JSValue]) extends JSValue
    case class JSDouble(d: Double) extends JSValue
	case class JSDouble2(d: InputWindow[Array[Char]]) extends JSValue
	case class JSString(d: InputWindow[Array[Char]]) extends JSValue
	case class JSString2(d: String) extends JSValue
    case class JSBool(b: Boolean) extends JSValue
    object JTrue extends JSValue
    object JFalse extends JSValue


    val trueValue = "true".toCharArray
    val falseValue = "false".toCharArray
    val comma = ",".toCharArray
    val close = "]".toCharArray

    /*val x = new {
    	@compileTimeOnly("dsad")
    	def y = 2
    }*/


  	object CSVImpl1 {
    	import fastparsers.framework.implementations.FastParsersCharArray._
    	import fastparsers.parsers.Parser
		val cvsParser = FastParsersCharArray {
			def cvs(p: Parser[JSValue]) = '[' ~> repsep(p, comma) <~ close ^^ JSArray
			def doubles = cvs(decimalNumber ^^ (x => JSDouble(x.toString.toDouble)))
			def bools = cvs((lit(trueValue) ~> success(JTrue)) | (lit(falseValue) ~> success(JFalse))) 
			//def strings = cvs(stringLit ^^ (x => JSString2(x.toString)))
			def strings = cvs(stringLit ^^ JSString)
		}
	}

  	object CSVImpl2 {
    	import fastparsers.framework.implementations.FastParsersCharArray._
		val cvsParserOpt = (FastParsersCharArray  {
			def primBools = ('t' ~ 'r' ~ 'u' ~ 'e' ~> success(JTrue)) | ('f' ~ 'a' ~ 'l' ~'s' ~ 'e' ~> success(JFalse)) 
			def bools = '[' ~> repsep(primBools, ',') <~ close// ^^ (x => JSArray(x))
		})
	}

	object CSV extends JavaTokenParsers {
		def cvs(p: Parser[JSValue]) = "[" ~> repsep(p, ",") <~ "]" ^^ (x => JSArray(x))
		def doubles = cvs(floatingPointNumber ^^ (y => JSDouble(y.toDouble)))
		def bools = cvs(("true" ~> success(JTrue)) | ("false" ~> success(JFalse))) 
		def strings = cvs(stringLiteral ^^ (y => JSString2(y)))
	}

}