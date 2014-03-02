/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

import scala.reflect.runtime.universe._
import scala.util.parsing.input._
object Test {
  def main(args: Array[String]) {
    import FastParsers._
    val parser = FastParser{
      //def rule1:Parser[Char] = /*'a' ~ */rule2
      def rule2 = 'e' ~ 'c' ~ 'c'
      def rule3 = 'a' ~ ('b' ~ 'c')  ~ rule5
      def rule4 = 'a' ~ 'b' ~ 'c'
      def rule5 = 'a' ~ (('b' ~ 'c') || 'b') ~ 'd'
      def rule6 = 'b'.rep(1,10)
      def rule7 = ('a' || 'b').rep(3,3)
      //def rule3 = 1 ~ 2 // problem?
      //def rule3 = (Elem('b')*) ~ (Elem('b')(1 to 10))
    }

    parser.rule7(new CharSequenceReader("bab"))  match {
      case Success() => println("yeah")
      case Failure(msg) => println("sigh : " + msg)
    }


    //test( Youhou(1))


   // parser.rule1("ab c")
    //parser.rule1("abc")
    //Elem('a') ~ Elem('b')    doesnt work, yes !
  }
}
