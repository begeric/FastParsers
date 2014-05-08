/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

//because warnings

import fastparsers.framework.getAST
import fastparsers.framework.implementations.FastArrayParsers
import fastparsers.framework.parseresult._
import fastparsers.input.InputWindow
import scala.collection.mutable.HashMap
import scala.language.reflectiveCalls
import scala.language.implicitConversions
import scala.reflect.ClassTag


object Test {

 def main(args: Array[String])  {
   //import FastParsers._
   import InputWindow._
   import scala.util.parsing.input._
   import fastparsers.parsers._

   object CharArrayParser extends FastArrayParsers[Char]
   import CharArrayParser._

   val parser = CharArrayParser {
    /* def rule1(x: Parser[(Char,Char)]) = '(' ~> x <~ ')'
     def rule2 = rule1('a' ~ 'g')
     def rule3 = 'a' ~ 'b' */
     //def rule4(x: Int) = repN('x',x)
     //def rule1(y: Parser[(Char,Char)]) = '(' ~> y <~ ')'
     //def rule2 = rule1(rule4)
     def rule = 'a' ~ 'b'
   }

   /*val parser2 = FastParser {
    /* def rule1 = parser.rule1(rule4)
     def rule4 = 'x' ~ 'y'*/
     def rule3 = parser.rule1(parser.rule4)
     def rule5 = 'a' ~ 'y'
   }   */
   getAST.get(parser)

   /*parser.rule4("ac".toCharArray) match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure: " + msg)
   } */
 }
}