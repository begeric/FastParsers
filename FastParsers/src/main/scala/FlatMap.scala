
import scala.annotation.compileTimeOnly
import scala.collection.mutable.HashMap
import scala.reflect.macros.whitebox.Context

trait FlatMapParsers {

  implicit class flatmapparsers[T](p: Parser[T]) {
    @compileTimeOnly("can’t be used outside FastParser")
    def flatMap[U](f: T => Parser[U]): Parser[U] = ???

    @compileTimeOnly("can’t be used outside FastParser")
    def >>[U](f: T => Parser[U]): Parser[U] = ???
  }

}

trait FlatMapImpl extends InlineRules with CombinatorImpl {
  self: ParseInput =>

  import c.universe._

  override def expandCallRule(tree: c.Tree, rulesMap: HashMap[String, RuleInfo], rulesPath: List[String]): c.Tree = tree match {
    case q"$a flatMap[$d]($f)"  => q"${expandCallRule(a, rulesMap, rulesPath)} flatMap[$d](${expandCallRuleFlatMap(f, rulesMap, rulesPath)})"
    case q"$a >>[$d]($f)"       => q"${expandCallRule(a, rulesMap, rulesPath)} >>[$d](${expandCallRuleFlatMap(f, rulesMap, rulesPath)})"
    case _                      => super.expandCallRule(tree, rulesMap, rulesPath)
  }


  private def expandCallRuleFlatMap(tree: c.Tree, rulesMap: HashMap[String, RuleInfo], rulesPath: List[String]): c.Tree = {
    def expandBody(body: c.Tree) = body match {
      case q"{..$body;$parser}" => q"{..$body;${expandCallRule(parser, rulesMap, rulesPath)}}"
      case _ => c.abort(c.enclosingPosition, "ill-formed body")
    }
    tree match {
      case q"(..$params => $x match {case ..$cases})" =>
        val trcases = cases.map {
          case cq"$pat => $body" => cq"$pat => ${expandBody(body)}"
        }
        q"(..$params => $x match {case ..$trcases})"
      case q"(..$params => $body)" => q"(..$params => ${expandBody(body)})"
      case _ => c.abort(c.enclosingPosition, show(tree))
    }
  }

  override def expand(tree: c.Tree, rs: ResultsStruct) = tree match {
    case q"$_.flatmapparsers[$d]($a)" => expand(a, rs)
    case q"$a flatMap[$d]($f)" => parseFlatMap(a, f, d, rs)
    case q"$a >>[$d]($f)" => parseFlatMap(a, f, d, rs)
    case _ => super.expand(tree, rs)
  }

  override def prettyPrint(tree: c.Tree) = tree match {
    case q"$_.flatmapparsers[$d]($a)" => prettyPrint(a)
    case q"$a flatMap[$d]($f)"        => prettyPrint(a) + ".flatMap(" + prettyPrint(f) + ")"
    case q"$a  >>[$d]($f)"            => prettyPrint(a) + ".>> (" + prettyPrint(f) + ")"
    case _ => super.prettyPrint(tree)
  }

  private def parseFlatMap(a: c.Tree, f: c.Tree, typ: c.Tree, rs: ResultsStruct) = f match {
    case q"(..$params => $body)" =>
      var results_tmp = new ResultsStruct()
      val result = TermName(c.freshName)
      val fm = TermName(c.freshName)
      val tree = c.untypecheck(mark {
        rollback =>
          q"""
        ${expand(a, results_tmp)}
        if (success) {
          val $fm = (..$params => ${expandFunction(body, result, rs)})
          $fm.apply(${combineResults(results_tmp)})
          if (!success)
            $rollback
        }
        else {
          $rollback
        }
      """
      })
      results_tmp.setNoUse
      rs.append(results_tmp)
      rs.append(result, typ)
      tree

    case _ => c.abort(c.enclosingPosition, "invalid function in rhs of flatMap")
  }

  private def expandFunction(func: c.Tree, result: TermName, rs: ResultsStruct) = {
    def expandBody(body: List[c.Tree], parser: c.Tree)(wrap: c.Tree => c.Tree) = {
      var results_tmp = new ResultsStruct()
      val tree = wrap(
        q"""{
        ..$body
        ${expand(parser, results_tmp)}
        $result = ${combineResults(results_tmp)}
      } """)
      results_tmp.setNoUse
      rs.append(results_tmp)
      tree
    }

    func match {
      case q"$x match {case ..$cases}" =>
        val trcases = cases.map {
          case cq"$pat => {..$body;$parser}" => expandBody(body, parser) {
            code => cq"$pat => $code"
          }
        }
        q"$x match {case ..$trcases}"
      case q"{..$body;$parser}" => expandBody(body, parser) {
        x => x
      }
      case _ => expand(func, rs)
    }
  }

}