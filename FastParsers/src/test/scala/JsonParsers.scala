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
      floatingPointNumber ^^ (_.toFloat) |
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
