/**
 * Found on the net
 */
import java.lang.CharSequence

class FastCharSequence(chars: Array[Char], val startBounds: Int, val endBounds: Int) extends CharSequence {
  def this(chars: Array[Char]) = this(chars, 0, chars.length)
  def this(input: String)      = this(input.toCharArray)

  def length(): Int = endBounds - startBounds

  def charAt(index: Int): Char = {
    if (index < length) {
      chars(index + startBounds)
    } else {
      throw new IndexOutOfBoundsException(s"$boundsInfo index: $index")
    }
  }

  def subSequence(start: Int, end: Int): CharSequence = {
    if (start >= 0 && start <= length && end >= 0 && end <= length) {
      new FastCharSequence(chars, startBounds + start, startBounds + end)
    } else {
      throw new IndexOutOfBoundsException(s"$boundsInfo start: $start, end $end")
    }
  }

  override def toString(): String = new String(chars, startBounds, length)

  private def boundsInfo = s"current: (startBounds: $startBounds, endBounds: $endBounds, length: $length, chars length: ${chars.length})"
}