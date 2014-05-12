package fastparsers.input

/**
 * Created by Eric on 22.04.14.
 */
trait CharArrayInput extends StringLikeInput {
  import c.universe._

  def inputType = typeOf[Array[Char]]
  override def inputWindowType: c.Type = typeOf[fastparsers.input.InputWindow.CharArrayStruct]

  override def getInputWindow(start: c.Tree, end: c.Tree): c.Tree = q"new fastparsers.input.InputWindow.CharArrayStruct($inputValue, $start, $end)"
}
