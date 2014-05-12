package fastparsers.parsers

import fastparsers.error._
import fastparsers.input.ParseInput
import scala.reflect.macros.whitebox.Context
import scala.collection.mutable.ListBuffer


/**
 * Provide the interface and the basics method needed to implement the transformation on parsers
 */
trait ParserImplBase { self: ParseInput with ParseError =>
  val c: Context

  import c.universe._

  /**
   * Represent a result variable
   * _1 : Variable name
   * _2 : Variable type
   * _3 : wheter or not it is temporary, which mean that it won't be used anymore to construct another result
   */
  type Result = (TermName, c.Tree, Boolean)

  /**
   * Structure to deal with the results of a rule expansion
   * Put inside all variables which will need to be initialized at the begining of the rule expansion
   */
  class ResultsStruct(var results: ListBuffer[Result]) {
    def this() = this(new ListBuffer[Result]())

    @deprecated("will be removed")
    protected def setNoUse = results = results.map(x => (x._1, x._2, false))
    protected def append(t: TermName, typ: c.Tree): Unit = append((t, typ, true))
    @deprecated("will be removed")
    protected def append(rs: ResultsStruct): Unit = rs.results.foreach(append(_))

    //TODO modifier !!
    def append(r: Result): Unit = results.append(r)


    def newVar(typ: c.Tree): TermName = {
      val t = c.freshName
      append(t, typ)
      t
    }
    def assignTo(result: TermName, value: c.Tree): c.Tree = {
      assert(results.exists(_._1 == result))
      q"$result = $value"
    }

    /**
     * Automatically create a variable, assign it with code and register it
     * @todo change typ: c.Tree to typ: c.Type
     * @param code The code to assign to the newly created variable
     * @param typ The typ of the variable
     * @return The code representing the assignation
     */
    def assignNew(code: c.Tree, typ: c.Type): c.Tree = assignNew(code, tq"$typ")

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

  /**
   * Class which automatically append its result to its parents as already used results
   * @todo clarify
   * @param dependence
   */
  class TemporaryResults(dependence: ResultsStruct) extends ResultsStruct {
    override def append(r: Result) = {
      super.append(r)
      dependence.append((r._1,r._2,false))
    }
  }

  trait IgnoreResult { self : ResultsStruct =>
    override def assignNew(code: c.Tree, typ: c.Tree) = q"()"
    override def combine = q"()"//c.abort(c.enclosingPosition, "cannot combine results while ignoring them")
    override def assignTo(result: TermName, value: c.Tree): c.Tree = q"()"
    override def temporary = new TemporaryResults(this) with IgnoreResult
  }


  /**
   * Get the "zero" value of a certain type
   * @todo not make it work on strings
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

  /**
   * Expand a combinator into an imperative code
   * @param tree the combinator to expand
   * @param r  ResultsStruct used to register the results
   * @return  The expanded code
   */
  def expand(tree: c.Tree, r: ResultsStruct): c.Tree = c.abort(c.enclosingPosition, "Not implemented combinator " + show(tree))

  /**
   * Prints a combinator in a readable way
   * @param tree Code containing the combinator to print
   */
  def prettyPrint(tree: c.Tree): String = "?" //TODO change ?
}

trait IgnoreResultsPolicy extends ParserImplBase { self: ParseInput with ParseError =>
  def ignoreResult(rs: ResultsStruct): ResultsStruct
}

trait DontIgnoreResults extends IgnoreResultsPolicy { self: ParseInput with ParseError =>
  override def ignoreResult(rs: ResultsStruct): ResultsStruct = rs.temporary
}

trait IgnoreResults extends IgnoreResultsPolicy { self: ParseInput with ParseError =>
  override def ignoreResult(rs: ResultsStruct): ResultsStruct = new ResultsStruct with IgnoreResult
}

