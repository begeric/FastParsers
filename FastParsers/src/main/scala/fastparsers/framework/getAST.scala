package fastparsers.framework

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context

/**
 * Created by Eric on 22.04.14.
 */
object getAST {
  def get(parser: Any):Any = macro getImpl

  def getImpl(c: Context)(parser: c.Tree): c.Tree = {
    import c.universe._

    val tmp = q"$parser"
    c.abort(c.enclosingPosition,show(tmp.tpe))
  }
}
