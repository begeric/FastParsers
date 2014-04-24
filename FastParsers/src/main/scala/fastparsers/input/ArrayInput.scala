package fastparsers.input

/**
 * Created by Eric on 22.04.14.
 * Input to deal with arrays
 */
trait ArrayInput extends ArrayLikeInput {

  protected val typ:c.WeakTypeTag[Elem]// = implicitly[c.WeakTypeTag[Elem]]

  import c.universe._

  def inputType = tq"Array[$typ]"
  def inputElemType = tq"$typ"

}
