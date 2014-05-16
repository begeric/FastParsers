import fastparsers.input.InputWindow
import fastparsers.parsers.Parser
import scala.util.parsing.combinator._
import scala.util.parsing.input._

import fastparsers.framework.getAST

/**
 * Created by Eric on 05.04.14.
 */
object CSVParsers {
  
    import fastparsers.framework.implementations.FastParsersCharArray._
    //GROS HACK
    import fastparsers.input.InputWindow.InputWindow

    sealed abstract class JSValue
    case class JSArray(arr: List[JSValue]) extends JSValue
    case class JSDouble(d: Double) extends JSValue
    case class JSBool(b: Boolean) extends JSValue
    object JTrue extends JSValue
    object JFalse extends JSValue

    val trueValue = "true".toCharArray
    val falseValue = "false".toCharArray
    val comma = ",".toCharArray
    val close = "]".toCharArray

    /*val cvsParser = FastParsersCharArray  {
		def primDouble = decimalNumber ^^ (y => JSDouble(y.toString.toDouble))
		def doubles = '[' ~> repsep(primDouble, comma) <~ close ^^ (x => JSArray(x))
		
		def primBools = (lit(trueValue) ~> success(JTrue)) | (lit(falseValue) ~> success(JFalse)) 
		def bools = '[' ~> repsep(primBools, comma) <~ close ^^ (x => JSArray(x))
	}*/
	
	val cvsParserOpt = (FastParsersCharArray  {
		def primBools = ('t' ~ 'r' ~ 'u' ~ 'e' ~> success(JTrue)) | ('f' ~ 'a' ~ 'l' ~'s' ~ 'e' ~> success(JFalse)) 
		def bools = '[' ~> repsep(primBools, ',') <~ close// ^^ (x => JSArray(x))
	})
	
	val cvsParser = FastParsersCharArray  {
		def cvs(p: Parser[JSValue]) = '[' ~> repsep(p, comma) <~ close ^^ (x => JSArray(x))
		def doubles = cvs(decimalNumber ^^ (y => JSDouble(y.toString.toDouble)))
		def bools = cvs((lit(trueValue) ~> success(JTrue)) | (lit(falseValue) ~> success(JFalse))) 
	}

	object CSV extends JavaTokenParsers {
		def cvs(p: Parser[JSValue]) = "[" ~> repsep(p, ",") <~ "]" ^^ (x => JSArray(x))
		def doubles = cvs(floatingPointNumber ^^ (y => JSDouble(y.toDouble)))
		def bools = cvs(("true" ~> success(JTrue)) | ("false" ~> success(JFalse))) 
	}


}