package fastparsers.input

/**
 * ArrayInput which work on Strings
 */
trait StringLikeInput extends ArrayLikeInput {

  import c.universe._

  def inputElemType = typeOf[Char]//c.typecheck(tq"Char",c.TYPEmode).tpe

  override def initInput(startpos: c.Tree, then: c.Tree) =
    super.initInput(startpos,
      q"""
        val inputpositioned = new fastparsers.tools.ToPosition.IndexedCharSeqToPosition(input)
        $then
      """
    )

  override def getPositionned(offset: c.Tree): c.Tree = q"inputpositioned.get($offset)"
}
