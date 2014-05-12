package fastparsers.input

/**
 * ArrayInput which work on Strings
 */
trait StringLikeInput extends ArrayLikeInput {

  import c.universe._

  private val inputpositioned = TermName(c.freshName("inputpositioned"))

  def inputElemType = typeOf[Char]

  override def initInput(startpos: c.Tree, then: c.Tree) =
    super.initInput(startpos,
      q"""
        val $inputpositioned = new fastparsers.tools.ToPosition.IndexedCharSeqToPosition($inputValue)
        $then
      """
    )

  override def getPositionned(offset: c.Tree): c.Tree = q"$inputpositioned.get($offset)"
}
