/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

//because warnings
import scala.language.reflectiveCalls
import scala.language.implicitConversions

object Test {
  val addressbook =
    """{
  "address book": {
    "name": "John Smith",
    "address": {
    "street": "10 Market Street",
    "city" : "San Francisco, CA",
    "zip" : 94111
    },
    "phone Nums": [
    "408 338-4238",
    "408 111-6892"
    ]
  }  }"""

 def main(args: Array[String]) {
   import FastParsers._

   val parser = FastParser{
     def value:Parser[Any] = obj | arr | stringLit | decimalNumber | "null" | "true" | "false"
     def obj:Parser[Any] = lit("{") ~> repsep(member,",") <~ "}"
     def arr:Parser[Any] = lit("[") ~> repsep(value,",") <~ "]"
     def member:Parser[Any] = stringLit ~> ":" ~> value
   }
   import scala.util.parsing.combinator._
   import scala.util.parsing.input._

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

   val file = scala.io.Source.fromFile("FastParsers/src/test/resources/json2").getLines mkString "\n"
   var res1:Any = null
   var res2:Any = null
   parser.value(file) match {
     case Success(x) => res1 = x
     case Failure(msg) => println("failure: " + msg)
   }

   JSON.parse(JSON.value,file) match {
     case JSON.Success(x,_) => res2 = x
     case _ => println("error")
   }

   println(res1)
   println(res2)

   println(res1 == res2)
 }
}