import FastParsers._
import scala.util.parsing.combinator._
import scala.util.parsing.input._

/**
 * Created by Eric on 05.04.14.
 */
object JsonParsers {

  val jsonparser = FastParser{
    def value:Parser[Any] = obj | arr | stringLit | decimalNumber | "null" | "true" | "false"
    def obj:Parser[Any] = lit("{") ~> repsep(member,",") <~ "}"
    def arr:Parser[Any] = lit("[") ~> repsep(value,",") <~ "]"
    def member:Parser[Any] = stringLit ~> ":" ~> value
  }

  object JSON extends JavaTokenParsers {
    def value: Parser[Any] = obj | arr | stringLiteral |
      floatingPointNumber ^^ (_.toDouble) |
      "null" | "true" | "false"
    def obj: Parser[Any] = "{" ~> repsep(member, ",") <~ "}"
    def arr: Parser[Any] = "[" ~> repsep(value, ",") <~ "]"
    def member: Parser[Any] = stringLiteral ~> ":" ~> value

    def wNum: Parser[Int] = bla
    def bla: Parser[Int] = (wholeNumber ^^ (_.toInt)) | "[" ~> wNum <~ "]"
  }

}
