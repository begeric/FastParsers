import scala.language.implicitConversions

object InputWindow {

  class InputWindow[Input](val in: Input,val start: Int,val end: Int){
    override def equals(x: Any) = x match {
      case s: InputWindow[Input] => s.in == in && s.start == start && s.end == end
      case _ => super.equals(x)
    }
  }

  class StringStruct(in: String,start: Int, end: Int)  extends InputWindow[String](in, start, end){

    private lazy val realValue = in.slice(start, end)

    def size = end - start
    def apply(n: Int) = in.charAt(n - start)

    override def toString = realValue
    override def equals(x: Any) = x match {
      case s: String => realValue == s
      case _ => super.equals(x)
    }
  }

  class CharArrayStruct(in: Array[Char],start: Int, end: Int)  extends InputWindow[Array[Char]](in, start, end){

    private lazy val realValue = in.slice(start, end)
    private lazy val realString = realValue.mkString

    def size = end - start
    def apply(n: Int) = in(n - start)

    override def toString = realString
    override def equals(x: Any) = x match {
      case s: Array[Char] => realValue == s
      case _ => super.equals(x)
    }
  }

  //WHY DOESNT IT WORK ?????
  implicit def CharArrayStruct2String(in: CharArrayStruct) = in.toString
  implicit def StringStruct2String(in: StringStruct) = in.toString
}