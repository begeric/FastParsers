/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

//because warnings

import scala.collection.mutable.HashMap
import scala.language.reflectiveCalls
import scala.language.implicitConversions


object Test {

 def main(args: Array[String])  {
  /* import FastParsers._
   import InputWindow._
   import scala.util.parsing.input._

   case class SuperStuff(x: Int) extends Positional

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

     def rule8:Parser[Int] = for (x <- number if x > 10) yield x

     def rule9 = for (a <- number;
                      op <- '+' ^^^ ((x:Int,y:Int) => x + y) | '-' ^^^ ((x:Int,y:Int) => x - y);
                      b <- number) yield (op(a,b))

     def rule10 = rep(not('a','b',('x','z')))

     def rule12 = if (2 > 3) 'a' ~ 'b' else 'c' ~ 'd'


     def rule13 = /*rep('a') ~> */positioned('b' ^^^ SuperStuff(1))

     def rule14 = until('a' || 'b','b' ~ 'c') ~ 'a'

     //def rule13 = acceptRec('a',('x','y'),('0','9')) || acceptRec('b') ~ acceptRec('b') ^^ (x => x)

     /*def expr = term chainl1 op1
     def term = number chainl1 op2
     def op1 = '+' ^^^ ((x:Int,y:Int) => x + y)  || '-' ^^^ ((x:Int,y:Int) => x - y)
     def op2 = '*' ^^^ ((x:Int,y:Int) => x * y)  || '/' ^^^ ((x:Int,y:Int) => x / y) */
   }

   parser.rule14("aabbabaabcaa") match {
     case Success(x : Positional) => println(x + " : " + x.pos)
     case Success(x) => println(x)
     case Failure(msg) => println("failure: " + msg)
   }       */

   object IntArrayParser extends FastArrayParsers[Int]
   import IntArrayParser._
   val arrayParser = IntArrayParser {
     def rule1:Parser[_] = rep(0 ~ 2) ~ 5
   }

   arrayParser.rule1(Array(0,2,5)) match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure: " + msg)
   }

   import java.io.FileInputStream
   val file = new FileInputStream("fsdf")
   file.
 }
}