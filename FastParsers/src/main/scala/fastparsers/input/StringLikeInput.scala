package fastparsers.input

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
        val inputpositioned = new fastparsers.tools.ToPosition.IndexedCharSeqToPosition(input)
        $then
      """
    )

  override def currentInput = q"input(inputpos)"

  override def slice(begin: c.Tree, end: c.Tree) = {
    q"input.slice($begin,$end)"
  }
  override def getPositionned(offset: c.Tree): c.Tree = q"inputpositioned.get($offset)"
}
