package fastparsers.parsers

import fastparsers.error._
import fastparsers.input.ParseInput
import scala.reflect.macros.whitebox.Context
import scala.collection.mutable.ListBuffer

/**
 * Provide the interface and the basics method needed to implement the transformation on fastparsers.parsers
 */
trait ParserImplBase { self: ParseInput with ParseError =>
  val c: Context

  import c.universe._


  type Result = (TermName, c.Tree, Boolean)

  /**
   * Structure to deal with the results of a rule expansion
   */
  class ResultsStruct(var results: ListBuffer[Result]) {
    def this() = this(new ListBuffer[Result]())

    @deprecated("will be removed")
    def setNoUse = results = results.map(x => (x._1, x._2, false))
    def append(r: Result):Unit = results.append(r)
    def append(t: TermName, typ: c.Tree):Unit = append((t, typ, true))
    @deprecated("will be removed")
    def append(rs: ResultsStruct):Unit = rs.results.foreach(append(_))

    def assignNew(code: c.Tree, typ: c.Tree): c.Tree = {
      val result = TermName(c.freshName)
      append(result,typ)
      q"$result = $code"
    }

    def temporary = new TemporaryResults(this)

    /**
     * Combine a list of results into either a tuple of result or into the same result
     */
    def combine: c.Tree = {
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
  }

  class TemporaryResults(dependence: ResultsStruct) extends ResultsStruct {
    override def append(r: Result) = {
      super.append(r)
      dependence.append((r._1,r._2,false))
    }
  }

  trait IgnoreResult { self : ResultsStruct =>
    override def assignNew(code: c.Tree, typ: c.Tree) = q""
    override def combine = c.abort(c.enclosingPosition, "cannot combine results while ignoring them")
    override def temporary = new TemporaryResults(this) with IgnoreResult
  }



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
