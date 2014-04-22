package fastparsers.input

/**
 * Created by Eric on 22.04.14.
 */
trait StringInput extends StringLikeInput {
  import c.universe._

  type Input = String
  def inputType = tq"String"
  override def inputWindowType: c.Tree = tq"fastparsers.input.InputWindow.StringStruct"

  override def getInputWindow(start: c.Tree, end: c.Tree): c.Tree = q"new fastparsers.input.InputWindow.StringStruct(input, $start, $end)"
}
