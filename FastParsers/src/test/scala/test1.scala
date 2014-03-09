import org.scalacheck.Prop.forAll
import org.scalacheck._
import scala.util.parsing.input._

object FastParsersSpecification extends Properties("FastParsers") {

  implicit def stringToCharSeqReader(s:String) = new CharSequenceReader(s)
  implicit def stringToStreamMarkedArray(s:String) = new StreamMarkedArray(s.toCharArray)
  import FastParsers._
  import Gen._
  
  val parser = FastParser{
	  def rule = phrase((range('a','z') || range('A','Z'))*)
  }
  
  property("rep range alpha str") = forAll (Gen.alphaStr){ (a: String) =>
    parser.rule(a) match {
      case Success(result) => true
	    case Failure(msg) => println(msg + " : " + a);false
	}
  }
}