/**
 * Created by Eric on 07.03.14.
 */

object StreamMarked {

  class NoMoreInput(msg:String,cause:Throwable = null) extends RuntimeException(msg,cause)

  trait StreamMarked[T] {
    def get:T
    def next:Unit
    def nextAndGet:T
    def mark:Int
    def rollBack(pos:Int)
    def offset:Int
    def atEnd:Boolean
    def notAtEnd:Boolean
  }


  trait EOF[T]{
    val value:T
  }

  implicit val EOFChar:EOF[Char] = new EOF[Char] {val value = '\032'}


 /* object StreamMarkedArray{               //why doesn't work
    implicit val EOFChar:EOF[Char] = new EOF[Char] {val value = '\032'}
  }  */

  class StreamMarkedArray[T](in:Array[T])(implicit eof:EOF[T]) extends StreamMarked[T]{
    private var pos:Int = 0
    private val size = in.size

    def get = try {
      in(pos)
    } catch {
      case e:ArrayIndexOutOfBoundsException => eof.value//throw new NoMoreInput("No more input at " + e.getMessage,e.getCause)
    }
    def next = {
      //if (pos < in.size)
        pos = pos + 1
    }
    def nextAndGet = {
      pos = pos + 1
      in(pos)
    }

    def mark = offset
    def rollBack(newpos:Int) = pos = newpos
    def offset = pos

    def atEnd = pos >= size
    def notAtEnd = pos < size
  }


}
//implicit def ArrayToStreamMarked[T](in:Array[T]) = new StreamMarkedArray(in)