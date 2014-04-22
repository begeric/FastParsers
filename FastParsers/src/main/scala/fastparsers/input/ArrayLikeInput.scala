package fastparsers.input

/**
 * Input on a array.
 *
 * Elem, Input, inputType and inputElemType must be set
 */
trait ArrayLikeInput extends ParseInput {

  import c.universe._

  def initInput(startpos: c.Tree, then: c.Tree) =
    q"""
      var inputpos = $startpos
      val inputsize = input.size
      $then
    """

  def currentInput = q"input(inputpos)"

  def advance = q"inputpos = inputpos + 1"

  def setpos(pos: c.Tree): c.Tree = q"inputpos = $pos"

  def mark(code: c.Tree => c.Tree) = {
    val input_tmp = TermName(c.freshName)
    q"""
      val $input_tmp = inputpos
      ${code(q"inputpos = $input_tmp")}
     """
  }

  def isEOI = q"inputpos >= inputsize"

  def isNEOI = q"inputpos < inputsize"

  def pos = q"inputpos"


  def inputsize = q"inputsize"

  def slice(begin: c.Tree, end: c.Tree) = {
    q"input.slice($begin,$end)"
  }
}
