/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

object Test {

 def main(args: Array[String]) {
   import FastParsers._

   val parser = FastParser{
     def rule1:Parser[Any] = 'a' ~ 'e' || '2' || rule2
     def rule2:Parser[Any] = 'x' ~ rule1
   }

   parser.rule1("xxxxae") match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure")
   }

	 println("new begining !")
 }
}