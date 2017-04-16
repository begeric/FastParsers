package fastparsers.input

/**
 * Created by Eric on 22.04.14.
 */
trait StringInput extends StringLikeInput {
  import c.universe._

  def inputType = typeOf[String]

  override def inputWindowType: c.Type = c.typecheck(tq"fastparsers.input.InputWindow.StringStruct", c.TYPEmode).tpe

  override def getInputWindow(start: c.Tree, end: c.Tree): c.Tree = q"new fastparsers.input.InputWindow.StringStruct(input, $start, $end)"
}
