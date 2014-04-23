/**
 * Created by Eric on 05.04.14.
 */

import fastparsers.framework.implementations.FastParsers
import fastparsers.input.InputWindow
import org.scalatest._
import FastParsers._
import InputWindow._

import fastparsers.parsers.Parser
import TestsHelper._

import scala.language.reflectiveCalls

class ParserSpecs extends FunSuite {

  val parser = FastParser{
    def rule1 = 'b' ~ 'a' ~ rule2
    def rule2 = 'c' ~ 'b'
    def rule3 = rep('a' ~ 'b',2,3) ~ 'c'
    def rule4 = phrase(rep1('a' ~ 'b'))
    def rule5 = phrase(rep('a' ~ 'b') ~ 'c')
    def rule6 = phrase(opt('a' ~ ('b' || 'c')))
    def rule10 = range('0','9') ~> rep(range('a','z')) <~ '0'
    def rule12 = guard('a' ~ 'b' ~ 'c') ~> rep(range('a','z'))
    def rule18 = phrase(rep('a',3,3) | (rep('a',2,2) ~ 'b') ^^ {case (x:List[Char],y:Char) => x ++ List(y)})
    def rule19 = phrase(rep('a',0,3)) | phrase(rep('a',0,4))
    def rule20 = phrase((rep('a',0,3) ~ 'b') ^^ {case (x:List[Char],y:Char) => x ++ List(y)} | rep('a' || 'b'))
    def rule21 = (rep(range('a','z')) filter {x => x.mkString == "salut" || x.mkString == "hello"})
    def rule22 = range('0','9').foldLeft(0,{(y:Int,x:Char) => x.asDigit +y})
    def rule27 = rep('a' ~ 'b') ~ rep('a')
    def rule28 = rep('a') ~ 'b'
    def rule31 = 'a' ~ ('b' withFailureMessage("JE VEUX UN 'b' ICI")) ~ 'c'
    def rule32 = range('0','9')
    def rule33 = rep(rule32,3,3)
    def rule34 = rule33 ~ rule33
    def rule35 = rep(wildcard)
    def rule36 = 'a' ~ takeWhile(_ != 'x')
    def rule37 = take(10)
    def rule38 = acceptIf(_ == 'b') ~ opt(acceptIf(x => x > 'a'))
    def rule39 = repN('a' ~ ('b' || 'c'),3)
    def rule40 = repsep('a' || 'b',repN('x' ~ 'y',2))
    def rule41 = '[' ~> repsep1(number,',') <~']'
    def rule42:Parser[(Char,Any)] = 'a' ~ rule43
    def rule43:Parser[Any] = 'b' || rule42
    def rule44 = range('0','9').foldLeft[Int](0,(acc,c) => acc + c.asDigit)
    def rule45 = (range('0','9') ^^ (_.asDigit)).reduceLeft[Int]((acc,c) => acc + c)
    def rule46 = range('0','9').foldRight(0,(c:Char,acc:Int) => acc + c.asDigit)
    def rule47 = (range('0','9') ^^ (_.asDigit)).reduceRight[Int]((c,acc) => acc + c)
    def rule48 =  wildcard.foldLeft("",(acc:String,c:Char) => acc + c)
    def rule49 =  wildcard.foldRight("",(c:Char,acc:String) => acc + c)
    def rule50 = rep1(stringLit)
    def rule51 = number ~ "hey" ~ number
    def rule52 = decimalNumber ^^ (_.toString)
    def rule53 = number >> (x => take(x.toString.toInt) ^^ (y => (x,y)))
    def rule54 = ('a' || 'b') >> {case 'a' => 'b' ~ rep('1');case 'b' => number}
    def rule55 = rule52 >> {case x => ':' ~> rep(rule32) ^^ (y => (x,y))}
    def rule56 = (number ^^ (_.toString)) >> {case b => ':' ~> rep(b)}
  }

  test("Rule1 test") {
    shouldSucced(parser.rule1){
      "bacb" gives (('b','a'),('c','b'))
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
      "ab" gives List(('a','b')),
      "abab" gives repeat(('a','b'),2),
      "ababab" gives repeat(('a','b'),3)
    )
    shouldFail(parser.rule4) (
      "", "a","b","aba","ababa"
    )
  }

  test("Rule5 test") {  //('a' ~ 'b').* ~ 'c'
    shouldSucced(parser.rule5)(
      "c" gives (Nil,'c'),
      "abc" gives (List(('a','b')),'c'),
      "ababababc" gives (repeat(('a','b'),4),'c')
    )
    shouldFail(parser.rule5) (
      "", "ab","abcab"
    )
  }

  test("Rule6 test") {  //('a' ~ ('b' || 'c')).?
    shouldSucced(parser.rule6)(
      "" gives None,
      "ab" gives Some(('a','b')),
      "ac" gives Some(('a','c'))
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

  test("Rule18 test") {  //phrase(rep('a',3,3) | (rep('a',2,2) ~ 'b') ^^ {case (x:List[Char],y:Char) => x ++ List(y)})
    shouldSucced(parser.rule18)(
      "aaa" gives List('a','a','a'),
      "aab" gives List('a','a','b')
    )
    shouldFail(parser.rule18) (
      "aa", "aaaa"
    )
  }

  test("Rule19 test") {  //phrase(rep('a',0,3)) | rep('a',0,4))
    shouldSucced(parser.rule19)(
      ""    gives Nil, "a" gives List('a'),
      "aa"  gives List('a','a'),
      "aaa" gives List('a','a','a'),
      "aaaa" gives List('a','a','a','a')
    )
    shouldFail(parser.rule19) (
      "aaaaa"
    )
  }

  test("Rule20 test") {  //phrase((rep('a',0,3) ~ 'b') ^^ {case (x:List[Char],y:Char) => x ++ List(y)} | rep('a' || 'b'))
    shouldSucced(parser.rule20)(
      "b"     gives List('b'),
      "ab"    gives List('a','b'),
      "aab"   gives List('a','a','b'),
      "aaab"  gives List('a','a','a','b'),
      ""      gives Nil,
      "aaaab" gives List('a','a','a','a','b')

    )
    shouldFail(parser.rule20) (
      "aba","cababds", "babba"
    )
  }

  test("Rule21 test") {  //((rep(range('a','z'))) filter {case x:List[_] => x.mkString == "salut" || x.mkString == "hello"})
    shouldSucced(parser.rule21)(
      "salut" gives List('s','a','l','u','t'),
      "hello" gives List('h','e','l','l','o')

    )
    shouldFail(parser.rule21) (
      "","salutt", "asalut","hellut"
    )
  }

  test("Rule22 test") {  //repFold(range('0','9'))(0){(y:Int,x:Char) => x.asDigit +y}
    shouldSucced(parser.rule22)(
      "123" gives 6,"" gives 0,"b" gives 0, "99" gives 18
    )
  }

  test("Rule34 test") {  //rule33 ~ rule33  -> rep(rep(0 - 9,3),2)
    shouldSucced(parser.rule34)(
      "123325" gives (List('1','2','3'),List('3','2','5'))
    )
    shouldFail(parser.rule34) (
      "1","12", "543","24334"
    )
  }

  test("Rule35 test") {  //rule33 ~ rule33  -> rep(rep(0 - 9,3),2)
    shouldSucced(parser.rule35)(
      "" gives Nil,
      "abcd" gives List('a','b','c','d')
    )
  }

  test("Rule36 test") {  //'a' ~ takeWhile(_ != 'x')
    shouldSucced(parser.rule36)(
      "a" gives ('a',""),
      "abbdsadxsadsad" gives ('a',"bbdsad")
    )

    shouldFail(parser.rule36) (
      "b", "", "dfdsf"
    )
  }

  test("Rule37 test"){
    shouldSucced(parser.rule37)(
      "aabbccddeeff" gives "aabbccddee",
      "aabbccddee" gives "aabbccddee"
    )
    shouldFail(parser.rule37) (
      "", "aabbccdde"
    )
  }

  test("Rule38 test"){
    shouldSucced(parser.rule38)(
      "b" gives ('b',None),
      "bb" gives ('b',Some('b'))
    )
    shouldFail(parser.rule38) (
      "", "a"
    )
  }
  test("Rule39 test"){     //repN('a' ~ ('b' || 'c'),3)
    shouldSucced(parser.rule39)(
      "ababab" gives repeat(('a','b'),3),
      "acacac" gives repeat(('a','c'),3),
      "abacac" gives ('a','b')::('a','c')::('a','c')::Nil
    )
    shouldFail(parser.rule39) (
      "", "abab", "abxbxb"
    )
  }

  test("Rule40 test"){//repsep(('a' || 'b'),repN('x' ~ 'y',2))
    shouldSucced(parser.rule40)(
      "" gives Nil,
      "a" gives List('a'),
      "b" gives List('b'),
      "axyxybxyxya" gives List('a','b','a'),
      "axyxybxyxyaxy" gives List('a','b','a'),
      "axyxybxxa" gives List('a','b'),
      "axyxybxya" gives List('a','b')
    )
  }

  test("Rule41 test"){//'[' ~> repsep1(number,',') <~']'
    shouldSucced(parser.rule41)(
      "[1]" gives List("1"),
      "[1, 2, 3,  40]" gives List("1","2","3","40")
    )
    shouldFail(parser.rule41) (
      "", "[]", "[1,2.3]", "[1,2,3"
    )
  }

  test("Rule42 test"){/*
    def rule42:fastparsers.parsers.Parser[(Char,Any)] = 'a' ~ rule43
    def rule43:fastparsers.parsers.Parser[Any] = 'b' || rule42
  */
    shouldSucced(parser.rule42)(
      "ab" gives ('a','b'),
      "aab" gives ('a',('a','b'))
    )
    shouldFail(parser.rule42) (
      "", "a", "b", "aaa"
    )
  }

  test("Rule44 test"){//range('0','9').foldLeft[Int](0,(acc,c) => acc + c.asDigit)
    shouldSucced(parser.rule44)(
      "" gives 0,
      "123" gives 6,
      "1234" gives 10
    )
  }

  test("Rule45 test"){
    shouldSucced(parser.rule45)(
      "123" gives 6,
      "1234" gives 10
    )
    shouldFail(parser.rule45)(
      ""
    )
  }

  test("Rule46 test"){
    shouldSucced(parser.rule46)(
      "" gives 0,
      "123" gives 6,
      "1234" gives 10
    )
  }

  test("Rule47 test"){
    shouldSucced(parser.rule47)(
      "123" gives 6,
      "1234" gives 10
    )
    shouldFail(parser.rule47)(
      ""
    )
  }

  test("Rule48 test"){    //wildcard.foldLeft("",(acc:String,c:Char) => acc + c)
    shouldSucced(parser.rule48)(
      "123" gives "123",
      "" gives ""
    )
  }

  test("Rule49 test"){   //wildcard.foldRight("",(acc:String,c:Char) => acc + c)
    shouldSucced(parser.rule49)(
      "123" gives "321",
      "" gives ""
    )
  }


  test("Rule50 test"){    //rep1(stringLit)
    shouldSucced(parser.rule50)(
      "\"abcd\" \"xcyxc\"" gives List("\"abcd\"","\"xcyxc\"")
    )

    shouldFail(parser.rule50)(
      "adas", "", "\"sdsa"
    )
  }

  test("Rule51 test"){  //number ~ "hey" ~ number
    shouldSucced(parser.rule51)(
      "1 hey 2" gives (("1","hey"),"2"),
      "43 hey 8782" gives (("43","hey"),"8782")
    )

    shouldFail(parser.rule51)(
      "adas", "", "546 hey ", "45", "458 h 487"
    )
  }

  test("Rule52 test"){  // def rule52 = decimalNumber
    shouldSucced(parser.rule52)(
      "3.3" gives "3.3",
      "3.3000" gives "3.3000",
      "3.3020" gives "3.3020",
      "789" gives "789",
      ".25" gives ".25",
      "45.10" gives "45.10",
      "7." gives "7."
    )

    shouldFail(parser.rule52)(
      "adas", "", "."
    )
  }

  test("Rule53 test"){//number >> (x => take(x) ^^ (y => (x,y)))
    shouldSucced(parser.rule53)(
      "5abcde" gives ("5","abcde"),
      "5abcdef" gives ("5","abcde"),
      "0" gives ("0","")
    )

    shouldFail(parser.rule53)(
      "", "5", "3aa"
    )
  }

  test("Rule54 test"){ //('a' || 'b') >> {case 'a' => 'b' ~ rep('1');case 'b' => number}
    shouldSucced(parser.rule54)(
      "ab" gives ('b',Nil),
      "ab1" gives ('b',List('1')),
      "ab2" gives ('b',Nil),
      "ab111" gives ('b',List('1','1','1')),
      "b52" gives "52"
    )

    shouldFail(parser.rule54)(
      "", "b", "a", "c"
    )
  }

  test("Rule55 test"){ //rule52 >> {case x => ':' ~ rep(rule32) ^^ (y => (x,y))}
    shouldSucced(parser.rule55)(
      "3.141592:123" gives ("3.141592",List('1','2','3')),
      "3.3:" gives ("3.3",Nil)
    )

    shouldFail(parser.rule55)(
      "", "2.5"
    )
  }

  test("Rule56 test"){ //(number ^^ (_.toString)) >> {case b => ':' ~> rep(b)}
    shouldSucced(parser.rule56)(
      "1:111111" gives repeat("1",6),
      "52:" gives Nil,
      "52:52" gives List("52"),
      "52:53" gives Nil,
      "12:121212" gives repeat("12",3)
    )

    shouldFail(parser.rule56)(
      "", "abc","12"
    )
  }
}
