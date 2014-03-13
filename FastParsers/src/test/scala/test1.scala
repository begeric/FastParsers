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
}