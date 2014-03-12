import collection.mutable.Stack
import org.scalatest._

class BasicRulesSpec extends FunSuite {
  import FastParsers._

  val parser = FastParser{
    def rule1 = 'b' ~ 'a' ~ rule2// ~ 'b' ~ 'l'
    def rule2 = 'c' ~ 'b'

    def rule3 = ('a' ~ 'b').rep(2,3) ~ 'c'
    def rule4 = phrase(('a' ~ 'b').+)
    def rule5 = phrase(('a' ~ 'b').* ~ 'c')
    def rule6 = phrase(('a' ~ ('b' || 'c')).?)

    def rule10 = range('0','9') ~> rep(range('a','z')) <~ '0'

    def rule12 = guard('a' ~ 'b' ~ 'c') ~ rep(range('a','z'))
    def rule13 = not('a' ~ 'b' ~ 'c') ~ rep(range('a','z'))


    def rule18 = phrase(rep('a',3,3) | (rep('a',2,2) ~ 'b'))
    def rule19 = phrase(rep('a',0,3)) | rep('a',0,4)
    def rule20 = phrase((rep('a',0,3) ~ 'b') | rep('a' || 'b'))

    def rule21 = ((rep(range('a','z'))) filter {case x:List[_] => x.mkString == "salut" || x.mkString == "hello"})

    def rule22 = repFold(range('0','9'))(0){(y:Int,x:Any) => x.asInstanceOf[Char].asDigit + 1 +y}
    def rule23 = range('0','9').repFold(1){(y:Int,x:Char) => x.asDigit * y}

    def rule24 = phrase(rep(range('a','z') || '?'))

    def rule25:Parser[Char] = 'a' ~ rule26
    def rule26:Parser[Char] = 'b' || rule25

    def rule27 = rep('a' ~ 'b') ~ rep('a')
    def rule28 = rep('a') ~ 'b'
    /*def rule29 = seq(List('s','a','l','u','t'))
    def rule30 = seq("salut")   */

    def rule31 = 'a' ~ ('b' withFailureMessage("JE VEUX UN 'b' ICI")) ~ 'c'
  }

  implicit def stringToStreamMarkedArray(s:String) = new StreamMarkedArray(s.toCharArray)
  implicit def strToInput(s:String) = new Input(s)

  class Input(in:String){
    def gives(result:Any) = InputAndResult(in,result)
  }

  case class InputAndResult(in:String, res:Any)

  def shouldSucced(rule:StreamMarked[Char] => ParseResult[Any])(tests:InputAndResult*) = {
       tests.foreach{
          x => rule(x.in) match {
            case Success(result) => assert(result == x.res, "on " + x.in + " expected " + x.res + " got " + result)
            case Failure(msg) => fail("Didn't succeed : " + msg + " on " + x.in)
          }
       }
  }

  def shouldFail(rule:StreamMarked[Char] => ParseResult[Any])(tests:String*) = {
    tests.foreach{
      str => rule(str) match {
        case Success(result) => fail("Wasn't supposed to succeed, result : " + result)
        case _ =>
      }
    }
  }

  def repeat[T](x:T,n:Int):List[T] = n match {
    case a if a <= 0 => Nil
    case _ => x::repeat(x,n - 1)
  }

  test("Rule1 test") {
    shouldSucced(parser.rule1){
      "bacb" gives ('b','a','c','b')
    }
    shouldFail(parser.rule1){
      "bbacb"
    }
  }
  test("Rule3 test") {             //('a' ~ 'b').rep(2,3) ~ 'c'
    shouldSucced(parser.rule3)(
      "ababc" gives (repeat(('a','b'),2),'c'),
      "abababc" gives (repeat(('a','b'),3),'c')
    )
    shouldFail(parser.rule3) (
       "a", "ab", "abc","ababababc","ababab","abababac"
    )
  }
  test("Rule4 test") {  //def rule4 = ('a' ~ 'b').+
    shouldSucced(parser.rule4)(
      "ab" gives List(('a','b')),  "abab" gives repeat(('a','b'),2),  "ababab" gives repeat(('a','b'),3)
    )
    shouldFail(parser.rule4) (
      "", "a","b","aba"
    )
  }

  test("Rule5 test") {  //('a' ~ 'b').* ~ 'c'
    shouldSucced(parser.rule5)(
      "c" gives (Nil,'c'),  "abc" gives (List(('a','b')),'c'),  "ababababc" gives (repeat(('a','b'),4),'c')
    )
    shouldFail(parser.rule5) (
      "", "ab","abcab"
    )
  }

  test("Rule6 test") {  //('a' ~ ('b' || 'c')).?
    shouldSucced(parser.rule6)(
      "" gives Nil,"ab" gives List(('a','b')),  "ac" gives List(('a','c'))
    )
    shouldFail(parser.rule6) (
      "ad", "acab","b","aba"
    )
  }

  test("Rule10 test") {  //range('0','9') ~> rep(range('a','z')) <~ '0'
    shouldSucced(parser.rule10)(
      "00" gives Nil,"0a0" gives List('a'),  "7abcd0" gives List('a','b','c','d')
    )
    shouldFail(parser.rule10) (
      "", "5","b","aba" ,"7abcd"
    )
  }

  test("Rule12 test") {  //guard('a' ~ 'b' ~ 'c') ~ rep(range('a','z'))
    shouldSucced(parser.rule12)(
      "abc" gives List('a','b','c'),"abcdef" gives List('a','b','c','d','e','f'),  "abc7" gives List('a','b','c')
    )
    shouldFail(parser.rule12) (
      "", "acc","v","sadsa"
    )
  }

  test("Rule13 test") {  //not('a' ~ 'b' ~ 'c') ~ rep(range('a','z'))
    shouldSucced(parser.rule13)(
      "" gives Nil,"abd" gives List('a','b','d'),"abddef" gives List('a','b','d','d','e','f'),  "dbd7" gives List('d','b','d')
    )
    shouldFail(parser.rule13) (
      "abc", "abcadsdsaf","abca5435"
    )
  }
  /*
    def rule19 = phrase(rep('a',0,3)) | rep('a',0,4)
    def rule20 = phrase((rep('a',0,3) ~ 'b') | rep('a' || 'b'))

   */

  test("Rule18 test") {  //phrase(rep('a',3,3) | (rep('a',2,2) ~ 'b'))
    shouldSucced(parser.rule18)(
      "aaa" gives List('a','a','a'),"aab" gives (List('a','a'),'b')
    )
    shouldFail(parser.rule18) (
      "aa", "aaaa"
    )
  }
}