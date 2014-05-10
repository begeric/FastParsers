
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


  object JSonImpl2 {
    import fastparsers.framework.implementations.FastParsersCharArray._
    val nullValue = "null".toCharArray
    val trueValue = "true".toCharArray
    val falseValue = "false".toCharArray
    val closeBracket = "}".toCharArray
    val closeSBracket = "]".toCharArray
    val comma = ",".toCharArray
    val points = ":".toCharArray
    val jsonparser = FastParsersCharArray{
      def value:Parser[Any] = whitespaces ~> (obj | arr | stringLit | decimalNumber | nullValue | trueValue | falseValue)
      def obj:Parser[Any] = '{' ~> repsep(member,comma) <~ closeBracket
      def arr:Parser[Any] = '[' ~> repsep(value,comma) <~ closeSBracket
      def member:Parser[Any] = stringLit ~ (lit(points) ~> value)
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
