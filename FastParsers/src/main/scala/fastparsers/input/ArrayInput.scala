package fastparsers.input

/**
 * Created by Eric on 22.04.14.
 * Input to deal with arrays
 */
trait ArrayInput extends ArrayLikeInput {

  import c.universe._
  def inputType     = c.typecheck(tq"Array[$inputElemType]",c.TYPEmode).tpe

}
