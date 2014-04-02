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

     def rule5 = lit("salut") ~ "hello"
     def rule6 = decimalNumber ~ stringLit ~ number

     def rule7 = range('a','d') ~ wildcard ~ acceptIf(_ != 'c')

     def rule8 = 'a' ~> ('b' || 'c') <~ 'd'

     //def rule9 = 'a' ~ 'b' ~ 'c' ^^ {case ((x,y),z) => x.toString + y + z}
     def rule9 = 'a' ~ 'b' ~ 'd' ^^ {case x ~ y ~ z => x.toString + y + z}

     def rule10 = ('a' ~ 'b').filter(x => false) withFailureMessage("erororororror")

     def rule11 = phrase(('b' || 'c') ^^^ 5)

     def rule12 = range('0','9').foldLeft[Int](0,(acc,c) => acc + c.asDigit)
     def rule13 = (range('0','9') ^^ (_.asDigit)).reduceLeft[Int]((acc,c) => acc + c)

     def rule14 = (('a' ~ 'b' ~ 'c') ^^^ 1).reduceLeft{(acc,c) => acc + c} ~ 'a' ~ 'b'

     def rule15 = range('0','9').foldRight(16,(c:Char,acc:Int) => acc / c.asDigit)

     def rule16 = (range('a','z') ^^ (_.toUpper.toString)).reduceRight{(c,acc) => acc + c}

   }
   //" -.3    \"hell\" -458"
   parser.rule15("81") match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure : " + msg)
   }

	 println("new begining !")
 }
}