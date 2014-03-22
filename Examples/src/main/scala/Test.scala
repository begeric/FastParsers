/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
import FastParsers._
import scala.collection.mutable
import StreamMarked._

import scala.util.parsing.input._
object Test {
 implicit def stringToCharSeqReader(s:String) = new CharSequenceReader(s)
 implicit def stringToStreamMarkedArray(s:String) = new StreamMarkedArray(s.toCharArray)
 //implicit def stringToArray(s:String) = s.toCharArray


 def main(args: Array[String]) {

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
    },
   "address book2": {
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
     },
     "address book3": {
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
      },
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
       },
      "address book2": {
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
        },
        "address book3": {
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
       }
    }
     """

   import scala.util.parsing.combinator._
   import scala.util.parsing.input._

   object JSON extends JavaTokenParsers {
     def value: Parser[Any] = obj | arr | stringLiteral |
       floatingPointNumber |
       "null" | "true" | "false"
     def obj: Parser[Any] = "{" ~ repsep(member, ",") ~ "}"
     def arr: Parser[Any] = "[" ~ repsep(value, ",") ~ "]"
     def member: Parser[Any] = stringLiteral ~ ":" ~ value

     def wNum: Parser[Int] = bla
     def bla: Parser[Int] = (wholeNumber ^^ (_.toInt)) | "[" ~> wNum <~ "]"
   }


   def toFloat(x:List[Char],y:List[List[Char]]) = (y match {
     case Nil => x.mkString
     case List(num) => x.mkString + "." + num.mkString
   }).toFloat

   /*val JSonParser = FastParser{
     def value:Parser[Any] = obj | arr | stringLit | float | seq("null") | seq("true") | seq("false")
     //def stringLit = '\"' ~> (except('\"')).repFold[java.lang.StringBuilder](new java.lang.StringBuilder(),(acc, c) => acc.append(c)) <~ '\"'
     def stringLit = '\"' ~> (takeWhile[Char](_ != '\"') ^^ {case x:Array[Char] => x.mkString})  <~ '\"'
     def float = rep1(range('0','9')) ~ opt('.' ~> rep(range('0','9'))) ^^ {case x:Tuple2[List[Char],List[List[Char]]] => toFloat(x._1,x._2)}
     def wss = takeWhile[Char](c => c == ' ' || c == '\n' || c == '\r')
     def obj:Parser[Any] = wss ~ '{' ~ wss ~> repsep(member,wss ~ ',' ~ wss) <~ wss ~ '}'
     def arr:Parser[Any] = wss ~ '[' ~ wss ~> repsep(value,wss ~ ',' ~ wss) <~ wss ~ ']'
     def member:Parser[Any] = stringLit ~ -(wss ~ ':' ~ wss) ~ value
   } */

   val JSonParser = FastParser{
     def value:Parser[Any] = obj | arr | stringLit | float | seq("null") | seq("true") | seq("false")
     //def stringLit = '\"' ~> (takeWhile[Char](_ != '\"') ^^ (_.mkString))  <~ '\"'
     def stringLit = '\"' ~> (except('\"')).repFold[java.lang.StringBuilder](new java.lang.StringBuilder(),(acc, c) => acc.append(c)) <~ '\"'
     def float = rep1(range('0','9')) ~ opt('.' ~> rep(range('0','9'))) ^^ {x => toFloat(x._1,x._2)}
     def wss = takeWhile[Char](c => c == ' ' || c == '\n' || c == '\r')
     def obj:Parser[Any] = wss ~ '{' ~ wss ~> repsep(member,wss ~ ',' ~ wss) <~ wss ~ '}'
     def arr:Parser[Any] = wss ~ '[' ~ wss ~> repsep(value,wss ~ ',' ~ wss) <~ wss ~ ']'
     def member:Parser[Any] = stringLit ~ -(wss ~ ':' ~ wss) ~ value
   }

   val lines = (scala.io.Source.fromFile("FastParsers\\src\\test\\resources\\tweet75").getLines mkString "\n")

   val tmp = new StreamMarkedArray(addressbook.toCharArray)

   (1 to 30).foreach{_=>

     val now = System.nanoTime
     JSON.parseAll(JSON.value,addressbook) match {
       case JSON.Success(result,_) =>
         val micros = (System.nanoTime - now) /1e6
         //println(result)
         println("Combinator : %fms".format(micros))
       case JSON.Failure(msg,_) => println("error : " + msg)
     }

     val now2 = System.nanoTime
     JSonParser.value(addressbook) match {
       case Success(result) =>
         val micros2 = (System.nanoTime - now2) /1e6
         //println(result)
         println("FastParser : %fms".format(micros2))
       case Failure(msg) => println("error : " + msg)
     }

   }

   /*val parser = FastParser {
      //def test = takeWhile[Char](_ != '7') ^^ {case x:Array[Char] => x.mkString}
      def test2:Parser[Any] = ('b' ~ 'c') | test
      def test:Parser[Any] = 'a' ~ test2

      def test3 = range('0','9').repFold(0,(acc:Int,c) => acc * 10 + c.asDigit)

   }

   parser.test3("4527") match {
     case Success(result) => println(result)
     case Failure(msg) => println("error : " + msg)
   }  */
 }
}