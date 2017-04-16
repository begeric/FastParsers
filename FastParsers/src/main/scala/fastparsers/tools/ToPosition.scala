package fastparsers.tools

/**
 * Created by Eric on 10.04.14.
 */

import java.util
import scala.collection.mutable.ArrayBuffer
import scala.util.parsing.input._

/**
 * Create a position object for an input
 */
object ToPosition {

  trait ToPosition[T] {
      def get(offset: Int): Position
  }

  type IndexedCharSeq[T] = {
    def apply(n: Int):Char
    def size: Int
    def slice(start: Int, end: Int): T
  }

  /**
   * TODOD change...
   */

  implicit class StringProxy(s: String) {
    def apply(n: Int):Char = s.charAt(n)
    def size: Int = s.length
    def slice(start: Int, end: Int): String = s.substring(start,end)
  }

  implicit class CharArrayProxy(s: Array[Char]) {
    def apply(n: Int):Char = s(n)
    def size: Int = s.length
    def slice(start: Int, end: Int): Array[Char] = util.Arrays.copyOfRange(s, start, start + end);
  }

  /*
    Almost copy pasted from scala.util.parsing.fastparsers.input.OffsetPosition @ https://github.com/scala/scala/blob/v2.10.2/src/library/scala/util/parsing/fastparsers.input/OffsetPosition.scala
   */
  class IndexedCharSeqToPosition[T](input: IndexedCharSeq[T]) extends ToPosition[IndexedCharSeq[T]] {

    val index = new ArrayBuffer[Int]()
    index += 0

    def computeIndexTill(pos: Int) {

      def compute(from: Int, to: Int) = {
        for (i <- from until to)
          if (input(i) == '\n')
            index += (i + 1)

        if (pos >= input.size)
          index += input.size
      }

      if (pos > index.last && index.last < input.size)
        compute(index.last + 1,Math.min(pos,input.size))
    }

    def lineFromOffset(pos: Int) = {
      computeIndexTill(pos)
      var lo = 0
      var hi = index.length - 1
      while (lo + 1 < hi) {
        val mid = (hi + lo) / 2
        if (pos < index(mid)) hi = mid
        else lo = mid
      }
      lo + 1
    }


    def get(offset: Int) = {
        val l = lineFromOffset(offset)
        new Position {
          override protected def lineContents = input.slice(index(line - 1), index(line)).toString   //TODO change that
          override def line = l
          override def column = offset - index(lineFromOffset(l) - 1) + 1
        }
    }
  }

}