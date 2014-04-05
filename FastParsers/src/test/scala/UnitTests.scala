import org.scalatest.FunSpec

/**
 * Created by Eric on 15.03.14.
 */
object UnitTests extends FunSpec{
  import org.scalatest._
  import FastParsers._


  implicit def strToInput(s:String) = new Input(s)

  class Input(in:String){
    def gives(result:Any) = InputAndResult(in,result)
  }

  case class InputAndResult(in:String, res:Any)

  def shouldSucced[T](rule:String => ParseResult[Any])(tests:InputAndResult*) = {
    tests.foreach{
      x => rule(x.in) match {
        case Success(result) => assert(result == x.res, "on " + x.in + " expected " + x.res + " got " + result)
        case Failure(msg) => fail("Didn't succeed : " + msg + " on \"" + x.in+"\"")
      }
    }
  }

  def shouldFail(rule:String => ParseResult[Any])(tests:String*) = {
    tests.foreach{
      str => rule(str) match {
        case Success(result) => fail("Wasn't supposed to succeed, result : " + result)
        case _ =>
      }
    }
  }

  def compare(s1:String, s2:String) = {
    s1.zip(s2).zipWithIndex.foreach{case ((a1,a2),i) =>
      if (a1 != a2)
        fail("error at " + i + " : " + s1.substring(i,i + 10) + " != " + s2.substring(i,i + 10))
    }
  }

  def repeat[T](x:T,n:Int):List[T] = n match {
    case a if a <= 0 => Nil
    case _ => x::repeat(x,n - 1)
  }

}