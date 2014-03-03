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
      def rule1 = 'b' ~ 'a'// ~ 'b' ~ 'l'
      def rule2 = ('c' || ('a' ~ 'd')) ~ 'b'
      def rule3 = ('a' ~ 'b').rep(2,3) ~ 'c'
      def rule4 = ('a' ~ 'b').+ ~ 'c'
      def rule5 = ('a' ~ 'b').* ~ 'c'
      def rule6 = ('a' ~ 'b').? ~ 'c'
      def rule7 = rule1
      def rule8 = ('b'.rep(0,-1) ~ ('a' || 'c')) ^^ {case (x:List[Char],y) => println(x.size);y}
      //def rule1:Parser[Char] = /*'a' ~ */rule2
      /*def rule2 = 'e' ~ 'c' ~ 'c'
      def rule3 = 'a' ~ ('b' ~ 'c') // ~ rule5
      def rule5 = 'a' ~ (('b' ~ 'c') || 'b') ~ 'd'
      def rule6 = 'b'.rep(1,10)  */
      //def rule7 = ('a' || 'b').rep(3,3)
      //def rule3 = 1 ~ 2 // problem?
      //def rule3 = (Elem('b')*) ~ (Elem('b')(1 to 10))
    }

    parser.rule8(new CharSequenceReader("bbbbbba")) match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    /*parser.rule6(new CharSequenceReader("c")) match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }  */

    /*parser.rule1(new CharSequenceReader("cb")) match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }   */
    /*parser.rule4(new CharSequenceReader("ba")) match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }     */
    /*parser.rule7(new CharSequenceReader("bac"))  match {
      case Success() => println("yeah")
      case Failure(msg) => println("sigh : " + msg)
    }    */



  }
}
