import org.scalacheck.Prop.forAll
import org.scalacheck._
import scala.util.parsing.input._
import StreamMarked._


object FastParsersSpecification extends Properties("FastParsers") {

  implicit def stringToCharSeqReader(s:String) = new CharSequenceReader(s)
  implicit def stringToStreamMarkedArray(s:String) = new StreamMarkedArray(s.toCharArray)
  import FastParsers._
  import Gen._
  
  val parser = FastParser{
	  def rule1 = phrase((range('a','z') || range('A','Z'))*)
    def rule2 = phrase(rep1(range('0','9')))
    def rule22 = repFold(range('0','9'))(0){(y:Int,x:Char) => x.asDigit +y}
    def rule23 = range('0','9').repFold(1,(y:Int,x:Char) => x.asDigit * y)

    def rule25:Parser[List[Char]] = ('a' ~ rule26) ^^ {case x:Tuple2[Char,List[Char]] => List(x._1) ++ x._2}
    def rule26:Parser[List[Char]] = ('b' ^^ {case x:Char => List(x)}) || rule25

    def rule27 = phrase(rule25)
  }
  
  property("rep range alpha str") = forAll (Gen.alphaStr){ (a: String) =>
    parser.rule1(a) match {
      case Success(result) => true
	    case Failure(msg) => println(msg + " : " + a);false
	  }
  }
  property("rep range num str") = forAll (Gen.numStr){ (a: String) =>
    parser.rule2(a) match {
      case Success(result) => a.size > 0
      case Failure(msg) => a.size == 0
    }
  }

  property("rep foldleft +  num str") = forAll (Gen.numStr){ (a: String) =>
    val shouldbe = a.map(_.asDigit).foldLeft(0)((a,b) => a + b)
    parser.rule22(a) match {
      case Success(result) => result == shouldbe
      case Failure(msg) => false
    }
  }

  property("rep foldleft *  num str") = forAll (Gen.numStr){ (a: String) =>
    val shouldbe = a.map(_.asDigit).foldLeft(1)((a,b) => a * b)
    parser.rule23(a) match {
      case Success(result) => result == shouldbe
      case Failure(msg) => false
    }
  }

  property("a+ ~ b") = forAll (Gen.nonEmptyListOf(Gen.choose('a','a')).map(_.mkString)){ (a: String) =>
    parser.rule27(a + "b") match {
      case Success(result) => true
      case Failure(msg) => false
    }
  }
}