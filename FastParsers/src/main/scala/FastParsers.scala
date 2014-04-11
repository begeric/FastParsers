import scala.language.experimental.macros
import scala.reflect.api.Universe
import scala.reflect.macros.whitebox.Context
import scala.annotation.compileTimeOnly
import scala.collection.mutable._


/**
 * General trait which create the basic needs of a FastParsers implementation.
 *
 * All it does is create the map of rulenames with their code (whitout modification).
 * It must be composed with some Rule transformer which will expand the rules wich will
 * be combined to form the final object by the RuleCombiner.
 * It must also be composed with a ParseInput to allow access on the input.
 */
trait FastParsersImpl {
  self: MapRules with RuleCombiner with ParseInput =>
  val c: Context

  import c.universe._

  def FastParser(rules: c.Tree): c.Tree = {
    val map = getBasicStructure(rules)
    val transformedMap = process(map)
    combine(transformedMap)
  }

  /**
   * Expand each rule in a imperative style without considering other rules (i.e def rule2 = rule1 is not expanded to the code of rule1)
   * @return An HashMap containing (rulename, corresponging code)
   */
  private def getBasicStructure(rules: c.Tree) = {
    val rulesMap = new HashMap[String, RuleInfo]()
    rules match {
      case q"{..$body}" =>
        body.foreach {
          case q"def $name:${d: TypeTree} = $b" =>
            val x = c.typecheck(b).tpe match {
              //case tq"$_.Parser[$d]" => c.abort(c.enclosingPosition, "correct parser type")
                                                           //HACK or not HACK ?
              case TypeRef(_, y, List(z)) if y.fullName == "Parser" => z //q"Any".tpe//q"var x:${d.tpe}" //check it is a parser
              case v => c.abort(c.enclosingPosition, "incorrect parser type " + show(v))
            }
            val TermName(nameString) = name
            val in = (nameString, (x, b))
            rulesMap += in
          case q"()" =>
          case x => c.abort(c.enclosingPosition, "body must only contain rule definition with the following form : def ruleName = body : " + x)
        }
      case _ =>
        c.abort(c.enclosingPosition, "ill-formed body, cannot be empty") //TODO can be empty ?
    }
    rulesMap
  }
}


/**
 * Example of a parser working on string.
 */
object FastParsers extends BaseParsers[Char, String] with RepParsers with TokenParsers with FlatMapParsers {
  def FastParser(rules: => Unit): Any = macro BaseImpl.FastParser
}

/**
 * Here is where the FastParsers implementation is composed to make an actual useful FastParsers
 */

class BaseImpl(val c: Context) extends FastParsersImpl with InlineRules
            with ParseRules with BaseParsersImpl with RepParsersImpl
            with TokenParsersImpl with FlatMapImpl with RuleCombiner
            with StringInput {

  override def FastParser(rules: c.Tree) = super.FastParser(rules) //why ??
}



class FastArrayParsers[T] extends BaseParsers[T, Array[T]] with RepParsers with FlatMapParsers {
  def apply(rules: => Unit): Any = macro ArrayParserImpl.ArrayParserImpl[T]
}

object ArrayParserImpl {
  def ArrayParserImpl[T: context.WeakTypeTag](context: Context)(rules: context.Tree): context.Tree =  {
    new FastParsersImpl with InlineRules
      with ParseRules with BaseParsersImpl with RepParsersImpl
      with FlatMapImpl with RuleCombiner with ArrayInput {

      type Elem = T
      type Input = Array[Elem]
      val c: context.type = context
      val typ = implicitly[c.WeakTypeTag[T]]

    }.FastParser(rules)
  }
}

