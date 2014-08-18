package fastparsers.error

import scala.reflect.macros.whitebox.Context

/**
 * Created by Eric on 23.04.14.
 */
/**
 * Trait used to deal with error in Parsers implementation
 */
trait ParseError {
  val c: Context
  import c.universe._

  def errorType: c.Tree

  val success = TermName(c.freshName("success"))

  /**
   * Initialize objects needed to deal with error and then run the code that follow
   * @param cont The code that follow
   */
  def initError(cont: c.Tree): c.Tree = cont

  /**
   * @param error The error message
   * @param inputPos The position in the input at which the error occurs
   * @return The code that deals with the error
   */
  def pushError(error: String, inputPos: c.Tree): c.Tree
}
