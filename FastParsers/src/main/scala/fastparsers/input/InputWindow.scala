package fastparsers.input

import scala.language.implicitConversions

object InputWindow {

  /**
   * Class used to represent a result by taking a reference to the original input and the position of its
   * subsequence in it
   */
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

    lazy val size = end - start

    private lazy val realValue = in.slice(start, end)
    private lazy val realString = {
      new String(in, start, end - start)
      /*val lb = new StringBuilder(size)
      var i = 0
      while (i < size){
        lb.append(in(i + start))
        i += 1
      }
      lb.result*/
    }

    def apply(n: Int) = in(n + start)

    override def toString = realString
    
    override def equals(x: Any) = x match {
      case s: Array[Char] if s.length == end - start => 
        var i = 0
        var cont = true
        val sLength = end - start
        while (cont && i < sLength){
          cont = s(i) == in(start + i)
          i += 1
        }
        cont
      case s: String if s.length == end - start => 
        var i = 0
        var cont = true
        val sLength = end - start
        while (cont && i < sLength){
          cont = s(i) == in(start + i)
          i += 1
        }
        cont
      case _ => super.equals(x)
    }
  }

  //WHY DOESNT IT WORK ?????
  implicit def CharArrayStruct2String(in: CharArrayStruct) = in.toString
  implicit def StringStruct2String(in: StringStruct) = in.toString
}