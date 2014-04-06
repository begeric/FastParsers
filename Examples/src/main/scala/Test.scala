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

 def main(args: Array[String])  {
   import FastParsers._

   val parser = FastParser{
     def rule2 = number >> ((x:Int) => take(x))
   }

   parser.rule2("5aaaaaaa") match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure : " + msg)
   }
 }
}