package fastparsers.error

/**
 * Created by Eric on 23.04.14.
 */
trait DefaultParseError extends ParseError{
  import c.universe._

  def errorType = tq"String"

  override def initError(cont: c.Tree): c.Tree = {
    q"""
      var error = " "
      $cont
    """
  }

  def pushError(error: String, inputPos: c.Tree): c.Tree =
    q"""error = $error + " at " + $inputPos """
}
