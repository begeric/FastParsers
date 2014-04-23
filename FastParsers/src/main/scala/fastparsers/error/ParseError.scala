package fastparsers.error

import scala.reflect.macros.whitebox.Context

/**
 * Created by Eric on 23.04.14.
 */
trait ParseError {
  val c: Context

  def errorType: c.Tree

  def initError(cont: c.Tree): c.Tree = cont

  def pushError(error: String, inputPos: c.Tree): c.Tree
}
