/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

import scala.collection.mutable.HashMap

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
     def member:Parser[Any] = stringLit ~ ":" ~ value
   }
   //val file = scala.io.Source.fromFile("FastParsers/src/test/resources/tweet75").getLines mkString "\n"
   parser.value(addressbook) match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure : " + msg)
   }

 }
}