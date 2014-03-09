import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import scala.util.parsing.input._

object FastParsersSpecification extends Properties("FastParsers") {

  implicit def stringToCharSeqReader(s:String) = new CharSequenceReader(s)
  implicit def stringToStreamMarkedArray(s:String) = new StreamMarkedArray(s.toCharArray)
  import FastParsers._
  
  val parser = FastParser{
	def rule = rep(range('a','z'))
  }
  
  property("rep range char") = forAll { (a: String) => 
    parser.rule(a) match {
      case Success(result) => true
	  case Failure(msg) => false
	}
  }
}