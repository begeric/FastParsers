package fastparsers.parsers

import scala.collection.mutable.HashMap
import fastparsers.framework._
import fastparsers.input._
import fastparsers.tools._
import fastparsers.framework.ruleprocessing.RulesTransformer
import fastparsers.error.ParseError

/**
 * Created by Eric on 22.04.14.
 * Implements FlatMap for fastparsers in the macro world.
 * A preprocessing phase is needed to modify the code inside the lambda/partial function
 */
trait FlatMapImpl extends RulesTransformer with ParserImplBase {
  self: ParseInput with ParseError with TreeTools =>

  import c.universe._


  override def transformRuleCalls(tree: c.Tree,
                              enclosingRule: RuleInfo,
                              rulesMap: HashMap[String, RuleInfo],
                              expandedRules: HashMap[String, RuleInfo],
                              rulesPath: List[String]): c.Tree = {

    def expandCallRuleFlatMap(tree: c.Tree): c.Tree = {
      def expandBody(body: c.Tree) = body match {
        case q"{..$body;$parser}" => q"{..$body;${transformRuleCalls(parser, enclosingRule, rulesMap, expandedRules, rulesPath)}}"
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

    tree match {
      case q"$a flatMap[$d]($f)"  => q"${transformRuleCalls(a, enclosingRule, rulesMap, expandedRules, rulesPath)} flatMap[$d](${expandCallRuleFlatMap(f)})"
      case q"$a >>[$d]($f)"       => q"${transformRuleCalls(a, enclosingRule, rulesMap, expandedRules, rulesPath)} >>[$d](${expandCallRuleFlatMap(f)})"
      case _                      => super.transformRuleCalls(tree, enclosingRule, rulesMap, expandedRules, rulesPath)
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
      var results_tmp = rs.temporary
      val result = rs.newVar(typ)
      val fm = TermName(c.freshName)
      c.untypecheck(mark {
        rollback =>
          q"""
        ${expand(a, results_tmp)}
        if ($success) {
          val $fm = (..$params => ${expandFunction(body, result, rs)})
          $fm.apply(${results_tmp.combine})
          if (!$success)
            $rollback
        }
        else {
          $rollback
        }
      """
      })

    case _ => c.abort(c.enclosingPosition, "invalid function in rhs of flatMap")
  }

  private def expandFunction(func: c.Tree, result: TermName, rs: ResultsStruct) = {
    def expandBody(body: List[c.Tree], parser: c.Tree)(wrap: c.Tree => c.Tree) = {
      var results_tmp = rs.temporary
      val tree = wrap(
        q"""{
        ..$body
        ${expand(parser, results_tmp)}
        $result = ${results_tmp.combine}
      } """)
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
