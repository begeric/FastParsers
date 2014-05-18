
import fastparsers.input.InputWindow
import fastparsers.parsers.Parser
import scala.util.parsing.combinator._
import scala.util.parsing.input._

/**
 * Created by Eric on 05.04.14.
 */
object JsonParsers {

  object JSonImpl1 {
    import fastparsers.framework.implementations.FastParsers._
    val jsonparser = FastParser{
      def value:Parser[Any] = whitespaces ~> (obj | arr | stringLit | decimalNumber | "null" | "true" | "false")
      def obj:Parser[Any] = '{' ~> repsep(member,",") <~ "}"
      def arr:Parser[Any] = '[' ~> repsep(value,",") <~ "]"
      def member:Parser[Any] = stringLit ~> ":" ~> value
    }
  }

  val nullValue = "null".toCharArray
  val trueValue = "true".toCharArray
  val falseValue = "false".toCharArray
  val closeBracket = "}".toCharArray
  val closeSBracket = "]".toCharArray
  val comma = ",".toCharArray
  val points = ":".toCharArray

  object JSonImpl2 {
    import fastparsers.framework.implementations.FastParsersCharArray._
    val jsonparser = FastParsersCharArray{
      def value:Parser[Any] = whitespaces ~> (obj | arr | stringLit | decimalNumber | nullValue | trueValue | falseValue)
      def obj:Parser[Any] = '{' ~> repsep(member,comma) <~ closeBracket
      def arr:Parser[Any] = '[' ~> repsep(value,comma) <~ closeSBracket
      def member:Parser[Any] = stringLit ~ (lit(points) ~> value)
    }
  }


  object JSonImpl3 {
    import fastparsers.framework.implementations.FastParsersCharArrayNoInline._
    val jsonparser = FastParsersCharArray{
      def value:Parser[Any] = whitespaces ~> (obj | arr | stringLit | decimalNumber | nullValue | trueValue | falseValue)
      def obj:Parser[Any] = '{' ~> repsep(member,comma) <~ closeBracket
      def arr:Parser[Any] = '[' ~> repsep(value,comma) <~ closeSBracket
      def member:Parser[Any] = stringLit ~ (lit(points) ~> value)
    }
  }

  object JSonImpl4 {
    import fastparsers.framework.implementations.FastParsersCharArrayDefaultErrors._
    val jsonparser = FastParsersCharArray{
      def value:Parser[Any] = whitespaces ~> (obj | arr | stringLit | decimalNumber | nullValue | trueValue | falseValue)
      def obj:Parser[Any] = '{' ~> repsep(member,comma) <~ closeBracket
      def arr:Parser[Any] = '[' ~> repsep(value,comma) <~ closeSBracket
      def member:Parser[Any] = stringLit ~ (lit(points) ~> value)
    }
  }

  object JSonImpl5 {
    import fastparsers.framework.implementations.FastParsersCharArrayIgnoreResults._
    val jsonparser = FastParsersCharArray{
      def value:Parser[Any] = whitespaces ~> (obj | arr | stringLit | decimalNumber | nullValue | trueValue | falseValue)
      def obj:Parser[Any] = '{' ~> repsep(member,comma) <~ closeBracket
      def arr:Parser[Any] = '[' ~> repsep(value,comma) <~ closeSBracket
      def member:Parser[Any] = stringLit ~ (lit(points) ~> value)
    }
  }

  object JSonImpl6 {
    import fastparsers.framework.implementations.FastParsersCharArray._
    import fastparsers.input.InputWindow.InputWindow
    val jsonparser = FastParsersCharArray{
      def value:Parser[Any] = whitespaces ~> (obj | arr | stringLit ^^ (_.toString) | decimalNumber | nullValue | trueValue | falseValue)
      def obj:Parser[Any] = '{' ~> repsep(member,comma) <~ closeBracket
      def arr:Parser[Any] = '[' ~> repsep(value,comma) <~ closeSBracket
      def member:Parser[Any] = stringLit ~ (lit(points) ~> value) ^^ {case (a, b) => (a.toString, b)}
    }
  }

  object JSonImplBoxed {
    import fastparsers.framework.implementations.FastParsersCharArray._
    //GROS HACK
    import fastparsers.input.InputWindow.InputWindow

    sealed abstract class JSValue
    case class JSObject(map: List[(InputWindow[Array[Char]], JSValue)]) extends JSValue
    case class JSArray(arr: List[JSValue]) extends JSValue
    case class JSDouble(d: InputWindow[Array[Char]]) extends JSValue
    case class JSDouble2(d: Double) extends JSValue
    case class JSString(s: InputWindow[Array[Char]]) extends JSValue
    case class JSBool(b: Boolean) extends JSValue
    case object JSNull extends JSValue

    val nullValue = "null".toCharArray
    val trueValue = "true".toCharArray
    val falseValue = "false".toCharArray
    val closeBracket = "}".toCharArray
    val closeSBracket = "]".toCharArray
    val comma = ",".toCharArray
    val points = ":".toCharArray

    val jsonparser = FastParsersCharArray  {
      def value:Parser[JSValue] = whitespaces ~>
       (
         obj |
         arr |
         stringLit ^^ {x => JSString(x)} |
         decimalNumber ^^ {x => JSDouble2(x.toString.toDouble)} |
         //decimalNumber ^^ {x => JSDouble(x)} |
         lit(nullValue) ^^^ JSNull |
         lit(trueValue) ^^^ JSBool(true) |
         lit(falseValue) ^^^ JSBool(false)
       )
      def obj:Parser[JSValue] = ('{' ~> repsep(member,comma) <~ closeBracket) ^^ {x => JSObject(x)}
      def arr:Parser[JSValue] = ('[' ~> repsep(value,comma) <~ closeSBracket) ^^ {x => JSArray(x)}
      def member:Parser[(InputWindow[Array[Char]], JSValue)] = stringLit ~ (lit(points) ~> value)
    }

  }

  object JSON extends JavaTokenParsers {
    def value: Parser[Any] = obj | arr | stringLiteral |
      floatingPointNumber |
      "null" | "true" | "false"
    def obj: Parser[Any] = "{" ~> repsep(member, ",") <~ "}"
    def arr: Parser[Any] = "[" ~> repsep(value, ",") <~ "]"
    def member: Parser[Any] = stringLiteral ~> ":" ~> value
  }

  //if needed
  def JsonEqual(a:Any,b:Any):Boolean = (a,b) match {
    case (x::xs,y::ys) => JsonEqual(x,y) && JsonEqual(xs,ys)
    case (Tuple2(x1,y1),JSON.~(x2,y2)) => JsonEqual(x1,x2) && JsonEqual(y1,y2)
    case (JSON.~(x1,y1),(x2,y2)) => JsonEqual(x1,x2) && JsonEqual(y1,y2)
    case _ => a == b

  }

}
