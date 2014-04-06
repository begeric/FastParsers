
import scala.annotation.compileTimeOnly
import scala.reflect.macros.whitebox.Context

trait FlatMapParsers{

  implicit class flatmapparsers[T](p:Parser[T]){
    @compileTimeOnly("can’t be used outside FastParser")
    def flatMap[U](f:T => Parser[U]):Parser[U] = ???
    @compileTimeOnly("can’t be used outside FastParser")
    def >>[U](f:T => Parser[U]):Parser[U] = ???
  }
}

trait FlatMapImpl extends InlineRules with  CombinatorImpl { self:ParseInput =>

  import c.universe._

  override def expand(tree: c.Tree, rs: ResultsStruct) = tree match{
    case q"FastParsers.flatmapparsers[$d]($a)" => expand(a,rs)
    case q"$a flatMap[$d]($f)" => parseFlatMap(a,f,d,rs)
    case q"$a >>[$d]($f)" => parseFlatMap(a,f,d,rs)
    case _ => super.expand(tree,rs)
  }

  private def parseFlatMap(a: c.Tree,f: c.Tree,typ: c.Tree,rs:ResultsStruct):c.Tree = f match{
    case q"($params => $body)" =>
      var results_tmp = new ResultsStruct()
      var results_tmp2 = new ResultsStruct()
      val result = TermName(c.freshName)
      val fm = TermName(c.freshName)
      val tree = mark { rollback =>
      q"""
        ${expand(a,results_tmp)}
        if (success) {
          val $fm = ($params => ${expandFunction(body,typ,result,rs)})
          $fm(${combineResults(results_tmp)})
          success = true
        }
        else {
          $rollback
        }
      """
      }
      results_tmp.setNoUse
      results_tmp2.setNoUse
      rs.append(results_tmp)
      rs.append(results_tmp2)
      rs.append(result,typ)
      tree

    case _ => c.abort(c.enclosingPosition,"invalid function in rhs of flatMap")
  }

  private def expandFunction(a: c.Tree,typ:c.Tree,result:TermName,rs:ResultsStruct):c.Tree = a match {
    case q"$x match {case ..$cases}" =>
      val trcases = cases.map{
        case cq"$pat => {..$body;$parser}" =>
          var results_tmp = new ResultsStruct()
          val t =
            cq"""
              $pat => {
              ..$body
              ${expand(parser,results_tmp)}
              $result = ${combineResults(results_tmp)}
            }"""
          results_tmp.setNoUse
          rs.append(results_tmp)
          t
      }
      q"$x match {case ..$trcases}"
    case q"{..$body;$parser}" =>
      var results_tmp = new ResultsStruct()
      val t =
        q"""{
          ..$body
          ${expand(parser,results_tmp)}
          $result = ${combineResults(results_tmp)}
        }"""
      results_tmp.setNoUse
      rs.append(results_tmp)
      t
    case _ => expand(a,rs)
  }

}