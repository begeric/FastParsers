/**
 * Created by Eric on 07.03.14.
 */
trait StreamMarked[T] {
  def get:T
  def next:Unit
  def nextAndGet:T
  def mark:Int
  def rollBack(pos:Int)
  def offset:Int
  def atEnd:Boolean
}

class StreamMarkedArray[T](in:Array[T]) extends StreamMarked[T]{
  private var pos:Int = 0

  def get = in(pos)
  def next = {
    if (pos < in.size)
      pos = pos + 1
  }
  def nextAndGet = {
    next
    get
  }

  def mark = offset
  def rollBack(newpos:Int) = pos = newpos //TODO check ?
  def offset = pos

  def atEnd = pos + 1 >= in.size
}

//implicit def ArrayToStreamMarked[T](in:Array[T]) = new StreamMarkedArray(in)