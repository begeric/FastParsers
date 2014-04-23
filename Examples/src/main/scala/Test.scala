/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

//because warnings

import fastparsers.framework.getAST
import fastparsers.framework.implementations.FastParsers
import fastparsers.framework.parseresult.{Failure, Success}
import fastparsers.framework.parseresult._
import fastparsers.input.InputWindow
import scala.collection.mutable.HashMap
import scala.language.reflectiveCalls
import scala.language.implicitConversions
import scala.reflect.ClassTag


object Test {

 def main(args: Array[String])  {
   import FastParsers._
   import InputWindow._
   import scala.util.parsing.input._


   val parser = FastParser {
     def rule1(x: Int) = '(' ~> repN('a',x) <~ ')'
     def rule2 = rule1(3)
     def rule3 = 'e' ~ 'f'
   }

   val parser2 = FastParser {
     def rule1 =  'c' ~ 'f' ~ parser.rule1(2)
   }

   parser2.rule1("cf(aa)dn") match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure: " + msg)
   }
   /*parser = FastParser {
     def rule1[T](x: Int, y: Int) = repN('v',x) ~ repN('b',y)
     def rule2 = rule1(2,5)
   }*/

  /* val parser = FastParser {
     def rule1: fastparsers.parsers.Parser[(Char,Any)] = 'a' ~ rule3
     def rule2 = 'b' ~ 'C'
     def rule3: fastparsers.parsers.Parser[Any] = 'b' || rule1
   } */
  //getAST.get(parser2)

   /*case class SuperStuff(x: Int) extends Positional


    val parser = FastParser{
     //def rule2 = number >> (x => take(x))
      def rule1 = 'a' ~ 'b'
      def rule2 = number >> (x => rule1 ~ take(x) ^^ (y => (x,y)))
      def rule3 = number >> {case 0 => rule1 ~ take(1);case x => take(2)}
      /*def rule4 = number >> (x => {take(x) ~ take(x)})   */
      def rule56 = (number ^^ (_.toString)) >> {case b => ':' ~> rep(b)}

      def rule5 = 'a' || 'b' || failure("so bad")    //type = Any (how to change that)
      def rule6 = 'a' || 'b' || success('c')

      def rule7 = raw(range('a','z') ~ ':' ~ rep(range('a','z') || range('0','9')))

      def rule8:fastparsers.parsers.Parser[Int] = for (x <- number if x > 10) yield x

      def rule9 = for (a <- number;
                       op <- '+' ^^^ ((x:Int,y:Int) => x + y) | '-' ^^^ ((x:Int,y:Int) => x - y);
                       b <- number) yield (op(a,b))

      def rule10 = rep(not('a','b',('x','z')))

      def rule12 = if (2 > 3) 'a' ~ 'b' else 'c' ~ 'd'


      def rule13 = /*rep('a') ~> */positioned('b' ^^^ SuperStuff(1))

      def rule14 = until('a' || 'b','b' ~ 'c') ~ 'a'

      def rule(x: Int) = repN('a',x) ^^ (y => println(y + " : " + x))

      def rule15(x: Int) = repN('a',x)

      def rule16(x: Int) = rule15(x)

      def rule17 = rep1(('a' || 'b') ~ 'c') || 'd'

      def rule18 = rule17 | 'e' ~ 'f'

      //def rule19[T](p: fastparsers.parsers.Parser[T]) = '5' ~ 'b'


      //def rule13 = acceptRec('a',('x','y'),('0','9')) || acceptRec('b') ~ acceptRec('b') ^^ (x => x)

      /*def expr = term chainl1 op1
      def term = number chainl1 op2
      def op1 = '+' ^^^ ((x:Int,y:Int) => x + y)  || '-' ^^^ ((x:Int,y:Int) => x - y)
      def op2 = '*' ^^^ ((x:Int,y:Int) => x * y)  || '/' ^^^ ((x:Int,y:Int) => x / y) */
   }


   //getAST.get(parser)

   parser.rule16("aaaa",4) match {
     //case Success(x : Positional) => println(x + " : " + x.pos)
     case Success(x) => println(x)
     case Failure(msg) => println("failure : " + msg)
   }  */

   /*

   import FastParsersCharArray._
   val parser = FastParsersCharArray {
     def rule1 = 'a' ~ 'b' ~ 'c' ~> ident //^^ (_.toString.toInt)
   }

   parser.rule1("abc sdadsaas".toCharArray) match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure : " + msg)
   }       */

  /* val jsonparser = FastParsersCharArray{
     def value:fastparsers.parsers.Parser[Any] = whitespaces ~> (obj | arr | stringLit | decimalNumber | "null".toCharArray | "true".toCharArray | "false".toCharArray)
     def obj:fastparsers.parsers.Parser[Any] = '{' ~> repsep(member,",".toCharArray) <~ "}".toCharArray
     def arr:fastparsers.parsers.Parser[Any] = '[' ~> repsep(value,",".toCharArray) <~ "]".toCharArray
     def member:fastparsers.parsers.Parser[Any] = stringLit ~> ":".toCharArray ~> value
   }
        */
      /*
   val bigFileName = "FastParsers/src/test/resources/" + "json.big1"
   var bigFile = scala.io.Source.fromFile(bigFileName).getLines mkString "\n"
   bigFile = bigFile.substring(0,200000) //+ bigFile + bigFile + bigFile + bigFile + bigFile + bigFile + bigFile + bigFile + bigFile + bigFile + bigFile
   val bigFileArray = bigFile.toCharArray

   val size = bigFile.size
   import java.util._
   import InputWindow._


   {
     val nb = 50
     val start = 20
     var average = 0.0
     (0 to nb).foreach { i =>
       var pos = 0
       var tmp = ' '
       var x = 0
       //var lit = new InputWindow[Array[Char]](bigFileArray,0,0)
       var lit = ""

       val now = System.nanoTime
       //val array = new Array[String](size)
       while (pos < size){
         tmp = bigFileArray(pos)
         if (tmp == 'c')
           x = x + 1
         lit = "sdakdsaklsjldajd" + pos.toString
         //lit = new InputWindow[Array[Char]](bigFileArray,0,10)
         //lit = array(pos)
         pos += 1
       }
       val micros = (System.nanoTime - now) /1e6
       if (i >= start)
         average += micros
     }
     println(average / (nb - start))
   }

   {
     val nb = 50
     val start = 20
     var average = 0.0
     (0 to nb).foreach { i =>
       var pos = 0
       var tmp = ' '
       var x = 0
       var lit = ""

       val now = System.nanoTime
       //val array = new Array[String](size)
       while (pos < size){
         tmp = bigFile.charAt(pos)
         if (tmp == 'c')
           x = x + 1
         lit = "sdakdsaklsjldajd" + pos.toString
         //lit = bigFile.slice(0,10)
         //lit = new InputWindow[String](bigFile,0,10)
         //lit = new String("sdakdsaklsjldajd")
         //lit = array(pos)
         pos += 1
       }
       val micros = (System.nanoTime - now) /1e6
       if (i >= start)
         average += micros
     }
     println(average / (nb - start))
   }   */

   /*object IntArrayParser extends fastparsers.framework.implementations.FastArrayParsers[Int]
   import IntArrayParser._
   val arrayParser = IntArrayParser {
     def rule1: fastparsers.parsers.Parser[_] = rep(0 ~ 2) ~ 5
   }

   arrayParser.rule1(Array(0,2,5)) match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure: " + msg)
   } */
 }
}