package fastparsers.error

import scala.reflect.macros.whitebox.Context

/**
 * Created by Eric on 23.04.14.
 */
trait ParseError {
  val c: Context

  def errorType: c.Tree
}
