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
    val elem: c.Type = c.typecheck(tq"Char",c.TYPEmode).tpe
    val input: c.Type = typeOf[Array[Char]]
    c.abort(c.enclosingPosition,show(parser.tpe))
  }
}
