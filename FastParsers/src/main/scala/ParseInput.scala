import scala.reflect.macros.whitebox.Context

/**
 * General interface to work on an input in the macro world.
 */
trait ParseInput {
  val c: Context
  type Elem
  type Input

  import c.universe._

  def inputType: c.Tree
  def inputElemType: c.Tree
  def inputWindowType: c.Tree = tq"InputWindow.InputWindow[$inputElemType]"

  def initInput(startpos: c.Tree, then: c.Tree): c.Tree

  def currentInput: c.Tree

  def advance: c.Tree

  def setpos(pos: c.Tree): c.Tree

  def mark(code: c.Tree => c.Tree): c.Tree

  def isEOI: c.Tree

  def isNEOI: c.Tree

  def pos: c.Tree

  def slice(begin: c.Tree, end: c.Tree): c.Tree

  def inputsize: c.Tree

  def getPositionned(offset: c.Tree): c.Tree = q"NoPosition"

  def getInputWindow(start: c.Tree, end: c.Tree): c.Tree = q"new InputWindow.InputWindow[$inputElemType](input, $start, $end)"
}

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


/**
 * ArrayInput which work on Strings
 */
trait StringLikeInput extends ArrayLikeInput {

  import c.universe._

  type Elem = Char
  def inputElemType = tq"Char"

  override def initInput(startpos: c.Tree, then: c.Tree) =
    super.initInput(startpos,
      q"""
        val inputpositioned = new ToPosition.IndexedCharSeqToPosition(input)
        $then
      """
    )

  override def currentInput = q"input(inputpos)"

  override def slice(begin: c.Tree, end: c.Tree) = {
    q"input.slice($begin,$end)"
  }
  override def getPositionned(offset: c.Tree): c.Tree = q"inputpositioned.get($offset)"
}

trait StringInput extends StringLikeInput {
  import c.universe._

  type Input = String
  def inputType = tq"String"
  override def inputWindowType: c.Tree = tq"InputWindow.StringStruct"

  override def getInputWindow(start: c.Tree, end: c.Tree): c.Tree = q"new InputWindow.StringStruct(input, $start, $end)"
}

trait CharArrayInput extends StringLikeInput {
  import c.universe._

  type Input = Array[Char]
  def inputType = tq"Array[Char]"
  override def inputWindowType: c.Tree = tq"InputWindow.CharArrayStruct"

  override def getInputWindow(start: c.Tree, end: c.Tree): c.Tree = q"new InputWindow.CharArrayStruct(input, $start, $end)"
}

trait ArrayInput extends ArrayLikeInput {

  protected val typ:c.WeakTypeTag[Elem]// = implicitly[c.WeakTypeTag[Elem]]

  import c.universe._

  def inputType = tq"Array[$typ]"
  def inputElemType = tq"$typ"

}