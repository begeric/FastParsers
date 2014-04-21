import scala.annotation.compileTimeOnly
import scala.collection.mutable.ListBuffer
import scala.reflect.macros.whitebox.Context

/**
 * Façade of a Parser. It only appears in the tree given to the macro in order to be expanded
 */
trait Parser[+T]


/**
 * Provide the interface and the basics method needed to implement the transformation on parsers
 */
trait CombinatorImpl {
  self: ParseInput =>
  val c: Context

  import c.universe._


  type Result = (TermName, c.Tree, Boolean)

  /**
   * Structure to deal with the results of a rule expansion
   */
  class ResultsStruct(var results: ListBuffer[Result]) {
    def this() = this(new ListBuffer[Result]())

    def setNoUse = results = results.map(x => (x._1, x._2, false))

    def append(r: Result) = results.append(r)

    def append(t: TermName, typ: c.Tree) = results.append((t, typ, true))

    def append(rs: ResultsStruct) = results.appendAll(rs.results)
  }

  /**
   * Combine a list of results into either a tuple of result or into the same result
   * @param results
   * @return
   */
  def combineResults(results: ListBuffer[Result]): c.Tree = {
    val usedResults = results.toList.filter(_._3)
    if (usedResults.size > 1) {
      //q"(..${usedResults.map(x => q"${x._1}")})"
      val first = usedResults(0)
      val sec = usedResults(1)
      val rest = usedResults.drop(2)
      rest.foldLeft(q"(${first._1},${sec._1})")((acc, e) => q"($acc,${e._1})")
    }
    else if (usedResults.size == 1)
      q"${usedResults(0)._1}"
    else
      q"Nil"
  }

  def combineResults(rs: ResultsStruct): c.Tree = combineResults(rs.results)

  /**
   * Get the "zero" value of a certain type
   * @param typ
   * @return
   */

  def zeroValue(typ: c.Tree) = {
    def fromString(str: String) = str match {
      case "Char" => q"' '"
      case "Int" => q"0"
      case "Float" => q"0"
      case "Double" => q"0.0D"
      case "String" => q""""""""
      case x if x.startsWith("List") => q"Nil"
      case _ => q"null"
    }
    typ match {
      case Ident(TypeName(name)) => fromString(name)
      case AppliedTypeTree(Ident(TypeName("List")), _) => q"Nil"
      case x => fromString(x.toString)
    }
  }

 /* def zeroValue(typ: c.Tree) =
  if (typ != null) typ.tpe match {
      case t if t =:= typeOf[Char]    => q"' '"
      case t if t =:= typeOf[Int]     => q"0"
      case t if t =:= typeOf[Float]   => q"0"
      case t if t =:= typeOf[Double]  => q"0.0D"
      case t if t =:= typeOf[String]  => q""""""""
      case t if t =:= typeOf[List[_]] => q"Nil"
      case _                          => q"null"
    }
  else  q"null" */


  def expand(tree: c.Tree, r: ResultsStruct): c.Tree = c.abort(c.enclosingPosition, "Not implemented combinator " + show(tree))

  def prettyPrint(tree: c.Tree): String = "_" //TODO change ?
}
