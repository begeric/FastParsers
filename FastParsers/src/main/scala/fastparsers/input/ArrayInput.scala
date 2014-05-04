package fastparsers.input

/**
 * Created by Eric on 22.04.14.
 * Input to deal with arrays
 */
trait ArrayInput extends ArrayLikeInput {

  //val typ: c.WeakTypeTag[_]// = implicitly[c.WeakTypeTag[Elem]]

  import c.universe._

  //def inputElemType = c.typecheck(tq"$typ",c.TYPEmode).tpe
  //def inputType     = c.typecheck(tq"Array[$typ]",c.TYPEmode).tpe

}
