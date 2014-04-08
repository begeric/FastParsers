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
   import FastParsers._

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
   }

   parser.rule7("a:bd5df") match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure : " + msg)
   }

 }
}