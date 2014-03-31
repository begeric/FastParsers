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
     def rule1:Parser[Any] = 'a' ~ 'e' || '3' || rule2
     def rule2:Parser[Any] = 'x' ~ rule1

     def rule3 = 'a' ~ opt(repN('b',3)) ~ 'c'

     def rule4 = repsep1('a','b')

     def  rule5 = lit("salut") ~ "hello"
     def rule6 = decimalNumber ~ stringLit ~ number
   }

   parser.rule6(" -.3    \"hell\" -458") match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure : " + msg)
   }

	 println("new begining !")
 }
}