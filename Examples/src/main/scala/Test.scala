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
    }
    }
    """

   val smallBook =
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
         }

         }
     """

   def toFloat(x:List[Char],y:List[List[Char]]) = (y match {
     case Nil => x.mkString
     case List(num) => x.mkString + "." + num.mkString
   }).toFloat

   def flattenRep(x:List[Tuple3[_,_,List[_]]]) = x.flatMap(y => (y._1,y._2) :: y._3)
   def flattenRep2(x:List[Tuple2[_,List[_]]]) = x.flatMap(y => y._1 :: y._2)

   val JSonParser = FastParser{
     def value:Parser[Any] = obj | arr | stringLit | float | seq("null") | seq("true") | seq("false")
     def stringLit = '\"' ~> (not('\"') ~> wildcard[Char]).repFold[StringBuilder](new StringBuilder(),(acc, c) => acc.append(c)) <~ '\"'
     def float = rep1(range('0','9')) ~ opt('.' ~> rep(range('0','9'))) ^^ {case x:Tuple2[List[Char],List[List[Char]]] => toFloat(x._1,x._2)}
     def wss = rep(' ' || '\t' || '\n' || '\r')
     def obj:Parser[Any] = wss ~ '{' ~ wss ~> repsep(member,wss ~ ',' ~ wss) <~ wss ~ '}'
     def arr:Parser[Any] = wss ~ '[' ~ wss ~> repsep(value,wss ~ ',' ~ wss) <~ wss ~ ']'
     //def obj:Parser[Any] = wss ~ '{' ~ wss ~> opt(member ~ rep(wss ~ ',' ~ wss ~> member)) <~ wss ~ '}' ^^ {case x:List[Tuple3[_,_,List[_]]] => flattenRep(x)}
     //def arr:Parser[Any] = wss ~ '[' ~ wss ~> opt(value ~ rep(wss ~ ',' ~ wss ~> value)) <~ wss ~ ']' ^^ {case x:List[Tuple2[_,List[_]]] => flattenRep2(x)}
     def member:Parser[Any] = stringLit ~ -(wss ~ ':' ~ wss) ~ value
   }

   /*val testRepSep = FastParser{
     def wss = rep(' ' || '\t' || '\n' || '\r')
     def parse = repsep('a',wss)
   }  */

   val lines = (scala.io.Source.fromFile("FastParsers\\src\\test\\resources\\tweet75").getLines mkString "\n")
   val tmp = new StreamMarkedArray("dsf   hf".toCharArray)

    val now2 = System.nanoTime
   JSonParser.value(addressbook) match {
    case Success(result) =>
      val micros2 = (System.nanoTime - now2) /1e6
      println(result)
      println("%f ms".format(micros2))
    case Failure(msg) => println("error : " + msg)
    }
 }
}