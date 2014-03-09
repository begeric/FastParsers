/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

import scala.util.parsing.input._
object Test {

  implicit def stringToCharSeqReader(s:String) = new CharSequenceReader(s)
  implicit def stringToStreamMarkedArray(s:String) = new StreamMarkedArray(s.toCharArray)

  def main(args: Array[String]) {
    import FastParsers._
    val parser = FastParser{
      def rule1 = 'b' ~ 'a'// ~ 'b' ~ 'l'
      def rule2 = ('c' || ('a' ~ 'd')) ~ 'b'
      def rule3 = ('a' ~ 'b').rep(2,3) ~ 'c'
      def rule4 = ('a' ~ 'b').+ ~ 'c'
      def rule5 = ('a' ~ 'b').* ~ 'c'
      def rule6 = ('a' ~ 'b').? ~ 'c'
      //def rule7 = rule1
      def rule8 = (rep('b',0,-1) ~ ('a' || 'c')) ^^ {case (x:List[_],y) => println(x.size);y}

      def rule9 = rep1(range('0','9'))

      def rule10 = range('0','9') ~> rep(range('a','z')) <~ '0'

      def rule11 = phrase('a' ~ 'b' ~ 'c')

      def rule12 = guard('a' ~ 'b' ~ 'c') ~ rep(range('a','z'))
      def rule13 = not('a' ~ 'b' ~ 'c') ~ rep(range('a','z'))

      def rule14 = rule1 ~ ('c' || 'd')

      def rule15 = 'a' ~ 'b' ~ 'c' ^^^ 1

      def rule16 = 'a' ~ ('b' ^^^ 2) ~ 'c'
      def rule17 = 'a' ~ ('b' ^^ {_ => 2}) ~ 'c'

      def rule18 = rep('a',3,3) | (rep('a',2,2) ~ 'b')
      def rule19 = phrase(rep('a',0,3)) | rep('a',0,4)
      def rule20 = (rep('a',0,3) ~ 'b') | rep('a' || 'b')

      def rule21 = ((rep(range('a','z'))) filter {case x:List[_] => x.mkString == "salut" || x.mkString == "hello"})

      def rule22 = repFold(range('0','9'))(0){(y:Int,x:Any) => x.asInstanceOf[Char].asDigit + 1 +y}
      def rule23 = range('0','9').repFold(1){(y:Int,x:Char) => x.asDigit * y}

      def rule24 = phrase(rep(range('a','z') || '?'))
    }


   parser.rule3("ababcc") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule8("bbbbbc") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule9("065614486") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule10("5sdawddggh0") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }
    parser.rule11("abcd") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule12("abcdfgha") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule13("dbcgha") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule14("bad") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule15("abc") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule16("abc") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule17("abc") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule18("aab") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule19("aaaa") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule20("aaabb") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule21("salu") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule22("5623") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule23("56") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }

    parser.rule24("") match {
      case Success(result) => println(result)
      case Failure(msg) => println("error : " + msg)
    }
  }
}
