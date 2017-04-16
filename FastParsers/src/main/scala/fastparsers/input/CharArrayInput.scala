package fastparsers.input

/**
 * Created by Eric on 22.04.14.
 */
trait CharArrayInput extends StringLikeInput {
  import c.universe._

  def inputType = c.typecheck(tq"Array[Char]",c.TYPEmode).tpe
  override def inputWindowType: c.Type = c.typecheck(tq"fastparsers.input.InputWindow.CharArrayStruct",c.TYPEmode).tpe

  override def getInputWindow(start: c.Tree, end: c.Tree): c.Tree = q"new fastparsers.input.InputWindow.CharArrayStruct(input, $start, $end)"
}
