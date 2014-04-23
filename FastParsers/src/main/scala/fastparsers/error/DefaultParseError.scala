package fastparsers.error

/**
 * Created by Eric on 23.04.14.
 */
trait DefaultParseError extends ParseError{
  import c.universe._

  def errorType = tq"String"
}
