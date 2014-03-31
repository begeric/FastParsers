import scala.reflect.macros.whitebox.Context

/**
 * General interface to work on an input in the macro world.
 */
trait ParseInput {
  val c:Context
  type Elem
  type Input

  def initInput(then:c.Tree) :c.Tree
  def currentInput:c.Tree
  def advance:c.Tree
  def advanceTo(offset:c.Tree):c.Tree
  def mark(code:c.Tree => c.Tree):c.Tree
  def isEOI:c.Tree
  def isNEOI:c.Tree
  def pos:c.Tree
  def offset:c.Tree
  def inputType:c.Tree
  def inputElemType:c.Tree
  def getChunk(typ:c.Tree,code:c.Tree => c.Tree):c.Tree
  def slice(begin:c.Tree,end:c.Tree):c.Tree
}

/**
 * Input on a array.
 *
 * Elem, Input, inputType and inputElemType must be set
 */
trait ArrayInput extends ParseInput {

  import c.universe._

  def initInput(then:c.Tree) =
    q"""
      var inputpos = 0
      val inputsize = input.size
      $then
    """
  def currentInput = q"input(inputpos)"
  def advance = q"inputpos = inputpos + 1"
  def advanceTo(offset:c.Tree) = q"inputpos += $offset"
  def mark(code:c.Tree => c.Tree) = {
    val input_tmp = TermName(c.freshName)
    q"""
      val $input_tmp = inputpos
      ${code(q"inputpos = $input_tmp")}
     """
  }
  def isEOI = q"inputpos >= inputsize"
  def isNEOI = q"inputpos < inputsize"
  def pos = q"inputpos"
  def offset = q"inputpos"

  def getChunk(typ:c.Tree,code:c.Tree => c.Tree) = {
    val beginPos = TermName(c.freshName)
    q"""
      val $beginPos = $pos
      ${code(q"")}
      if ($isEOI)
        input.slice($beginPos,$pos - 1)
      else
        input.slice($beginPos,$pos)
    """
  }

  def slice(begin:c.Tree,end:c.Tree):c.Tree = {
    q"input.slice($begin,$end)"
  }
}

/**
 * ArrayInput which work on Strings
 */
trait StringInput extends ArrayInput{
  import c.universe._

  type Elem = Char
  type Input = String
  def inputType = tq"String"
  def inputElemType = tq"Char"

  override def currentInput = q"input.charAt(inputpos)"

  override def getChunk(typ:c.Tree,code:c.Tree => c.Tree) = {
    val beginPos = TermName(c.freshName)
    q"""
      val $beginPos = $pos
      ${code(q"")}
      if ($isEOI)
        input.substring($beginPos,$pos - 1)
      else
        input.substring($beginPos,$pos)
    """
  }

  override def slice(begin:c.Tree,end:c.Tree):c.Tree = {
    q"input.substring($begin,$end)"
  }
}