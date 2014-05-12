package fastparsers.input

/**
 * Input on a array-like.
 *
 * Elem, Input, inputType and inputElemType must be set
 */
trait ArrayLikeInput extends ParseInput {

  import c.universe._

  private val inputpos = TermName(c.freshName("inputpos"))
  private val inputlength = TermName(c.freshName("inputsize"))


  def initInput(startpos: c.Tree, then: c.Tree) =
    q"""
      var $inputpos = $startpos
      val $inputlength = $inputValue.size
      $then
    """

  def currentInput = q"$inputValue($inputpos)"

  def advance = q"$inputpos = $inputpos + 1"

  def setpos(pos: c.Tree): c.Tree = q"$inputpos = $pos"

  def mark(code: c.Tree => c.Tree) = {
    val input_tmp = TermName(c.freshName)
    q"""
      val $input_tmp = $inputpos
      ${code(q"$inputpos = $input_tmp")}
     """
  }

  def isEOI = q"$inputpos >= $inputlength"

  def isNEOI = q"$inputpos < $inputlength"

  def pos = q"$inputpos"

  def inputsize = q"$inputlength"

  def slice(begin: c.Tree, end: c.Tree) = {
    q"$inputValue.slice($begin,$end)"
  }
}
