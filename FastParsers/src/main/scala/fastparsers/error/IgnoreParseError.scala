package fastparsers.error

/**
 * Created by Eric on 23.04.14.
 */
trait IgnoreParseError extends DefaultParseError{
  import c.universe._
  override def pushError(error: String, inputPos: c.Tree): c.Tree = q"()"
}
