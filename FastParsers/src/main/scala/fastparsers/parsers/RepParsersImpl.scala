package fastparsers.parsers

import fastparsers.input.ParseInput
import fastparsers.error.ParseError

/**
 * Created by Eric on 22.04.14.
 * Implementation of RepParsers
 */
trait RepParsersImpl extends ParserImplBase { self: ParseInput  with ParseError =>

  import c.universe._

  override def expand(tree: c.Tree, rs: ResultsStruct) = tree match {
    case q"$_.repParser[$d]($a)"                  => expand(a, rs)
    case q"$_.rep[$d]($a,$min,$max)"              => parseRep(a, d, min, max, rs)
    case q"$_.rep1[$d]($a)"                       => parseRep(a, d, q"1", q"-1", rs)
    case q"$_.repN[$d]($a,$n)"                    => parseRep(a, d, n, n, rs)
    case q"$_.opt[$d]($a)"                        => parseOpt(a, d, rs)
    case q"$_.repsep[$typ,$d]($a,$b)"             => parseRepsep(a, b, typ, atLeastOnce = false, rs)
    case q"$_.repsep1[$typ,$d]($a,$b)"            => parseRepsep(a, b, typ, atLeastOnce = true, rs)
    case q"$_.until[$typ,$d]($a,$b)"              => parseUntil(a, b, typ, rs)
    case q"$a foldLeft[$d]($init,$f)"             => parseFoldLeft(a, init, f, d, rs)
    case q"$a foldRight[$d,$ptype]($init,$f)"     => parseFoldRight(a, init, f, d, ptype, rs)
    case q"$a reduceLeft[$d]($f)"                 => parseReduceLeft(a, f, d, rs)
    case q"$a reduceRight[$d]($f)"                => parseReduceRight(a, f, d, rs)
    case _                                        => super.expand(tree, rs)
  }

  override def prettyPrint(tree: c.Tree) = tree match {
    case q"$_.repParser[$d]($a)"         => prettyPrint(a)
    case q"$_.rep[$d]($a,$min,$max)"     => "rep(" + prettyPrint(a) + ", " + show(min) + ", " + show(max) + ")"
    case q"$_.rep1[$d]($a)"              => "rep1(" + prettyPrint(a) + ")"
    case q"$_.repN[$d]($a,$n)"           => "repN(" + prettyPrint(a) + ", " + show(n) + ")"
    case q"$_.opt[$d]($a)"               => "opt(" + prettyPrint(a) + ")"
    case q"$_.repsep[$typ,$d]($a,$b)"    => "repsep(" + prettyPrint(a) + ", " + prettyPrint(b) + ")"
    case q"$_.repsep1[$typ,$d]($a,$b)"   => "repsep1(" + prettyPrint(a) + ", " + prettyPrint(b) + ")"
    case q"$_.until[$typ,$d]($a,$b)"     => "until(" + prettyPrint(a) + ", " + prettyPrint(b) + ")"
    case q"$a foldLeft[$d]($init,$f)"             => prettyPrint(a) + " foldLeft(" + show(init) + ", " + prettyPrint(f) + ")"
    case q"$a foldRight[$d,$ptype]($init,$f)"     => prettyPrint(a) + " foldRight(" + show(init) + ", " + prettyPrint(f) + ")"
    case q"$a reduceLeft[$d]($f)"                 => prettyPrint(a) + " reduceLeft(" + prettyPrint(f) + ")"
    case q"$a reduceRight[$d]($f)"                => prettyPrint(a) + " reduceRight(" + prettyPrint(f) + ")"
    case _                                        => super.prettyPrint(tree)
  }

  private def parseRep(a: c.Tree, typ: c.Tree, min: c.Tree, max: c.Tree, rs: ResultsStruct) = {
    val counter     = TermName(c.freshName)
    val cont        = TermName(c.freshName)
    val tmp_result  = TermName(c.freshName)
    var results_tmp = rs.temporary

    val innerWhileTree = mark {
      rollback =>
        q"""
          ${expand(a, results_tmp)}
          if ($success) {
              $tmp_result += ${results_tmp.combine}
              if ($counter + 1 == $max) $cont = false
          }
          else {
              $success = $counter >= $min
              $cont = false
              if (!$success)
                ${pushError("expected at least " + show(min) + " occurence(s) for rep(" + prettyPrint(a) + ", " + show(min) + ", " + show(max) + ")", pos)}
              else
                $rollback
          }
        """
    }

    mark { rollback =>
        q"""
          var $counter = 0
          var $cont = true
          val $tmp_result = new ListBuffer[$typ]()
          $success = $min == 0
          while($cont){
            $innerWhileTree
            $counter = $counter + 1
          }
          if (!$success) {
            $rollback
          }
          else {
             ${rs.assignNew(q"$tmp_result.toList",tq"List[$typ]")}
          }
        """
    }
  }

  private def parseOpt(a: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    var results_tmp = rs.temporary
    val result = rs.newVar(tq"Option[$typ]")
    mark { rollback =>
        q"""
        ${expand(a, results_tmp)}
        if ($success) {
          ${rs.assignTo(result, q"Some(${results_tmp.combine})")}
        }
        else {
          $rollback
          ${rs.assignTo(result, q"None")}
          $success = true
        }
      """
    }
  }

  private def parseRepsep(a: c.Tree, sep: c.Tree, typ: c.Tree, atLeastOnce: Boolean, rs: ResultsStruct) = {
    var results_tmp = rs.temporary
    var results_tmp2 = rs.temporary
    val cont = TermName(c.freshName)
    val tmp_result = TermName(c.freshName)
    val result = rs.newVar(tq"List[$typ]")

    val innertree2 = mark {  rollback =>
        q"""
          ${expand(sep, results_tmp2)}
           if (!$success) {
            $cont = false
            $rollback
           }
        """
    }

    val innertree1 = mark { rollback =>
        q"""
          ${expand(a, results_tmp)}
          if ($success) {
             $tmp_result += ${results_tmp.combine}
             $innertree2
          }
          else {
            $cont = false
            $rollback
          }
         """
    }

    val assignSuccess =
      if (atLeastOnce)
        mark { rollback =>
            q"""
          if ($tmp_result.size == 0) {
            $rollback
            $success = false
          }
          else {
            ${rs.assignTo(result, q"$tmp_result.toList")}
            $success = true
           }
        """
        }
      else {
        q"""
          ${rs.assignTo(result, q"$tmp_result.toList")}
          $success = true
        """
      }

    q"""
      var $cont = true
      val $tmp_result = new ListBuffer[$typ]()
      while($cont) {
        $innertree1
      }
      $assignSuccess
    """
  }

  private def parseUntil(a: c.Tree, end: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    var results_tmp = rs.temporary

    val cont = TermName(c.freshName)
    val tmp_result = TermName(c.freshName)

    val innertree2 = mark { rollback =>
      q"""
        ${expand(end, rs.temporary)}
        if ($success)
          $cont = false
        else
          $rollback
      """
    }

    val innertree = mark { rollback =>
      q"""
        ${expand(a,results_tmp)}
        if ($success) {
          $tmp_result += ${results_tmp.combine}
          $innertree2
        }
        else {
          $rollback
          $cont = false
         }
      """
    }

    q"""
      var $cont = true
      val $tmp_result = new ListBuffer[$typ]()
      while($cont) {
        $innertree
      }
      ${rs.assignNew(q"$tmp_result.toList", tq"List[$typ]")}
      $success = true
    """
  }

  private def parseFoldLeft(a: c.Tree, init: c.Tree, f: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    var results_tmp = rs.temporary
    val cont = TermName(c.freshName)
    val tmp_f = TermName(c.freshName)
    val result = rs.newVar(typ)

    val inner = mark {
      rollback =>
        q"""
        ${expand(a, results_tmp)}
         if ($success){
           ${rs.assignTo(result, q"$tmp_f($result,${results_tmp.combine})")}
          }
         else {
          $cont = false
          $rollback
         }
      """
    }
    q"""
      val $tmp_f = $f
      var $cont = true
      ${rs.assignTo(result, init)}
      while($cont){
        $inner
      }
      $success = true
    """
  }

  private def buffer(a: c.Tree, typ: c.Tree, rs: ResultsStruct)(process: TermName => c.Tree) = {
    var results_tmp = rs.temporary
    val cont = TermName(c.freshName)
    val buffer = TermName(c.freshName)

    val buffering = mark { rollback =>
        q"""
        ${expand(a, results_tmp)}
        if ($success)
          $buffer += ${results_tmp.combine}
        else
          $cont = false
      """
    }

    q"""
     var $cont = true
     val $buffer = new ListBuffer[$typ]()
     while($cont){
       $buffering
     }
     ${process(buffer)}
    """
  }


  private def parseFoldRight(a: c.Tree, init: c.Tree, f: c.Tree, typ: c.Tree, parserType: c.Tree, rs: ResultsStruct) = {
    buffer(a, parserType, rs) { buffer =>
      q"""
       ${rs.assignNew(q"$buffer.foldRight[$typ]($init)($f)", typ)}
       $success = true
      """
    }
  }

  private def parseReduceLeft(a: c.Tree, f: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    var results_tmp = rs.temporary
    mark { rollback =>
      q"""
       ${expand(a, results_tmp)}
       if ($success){
          ${parseFoldLeft(a, results_tmp.combine, f, typ, rs)}
       }
       else {
        $success = false
        ${pushError(prettyPrint(a) + " reduceLeft failed", pos)}
        $rollback
       }
      """
    }
  }


  private def parseReduceRight(a: c.Tree, f: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    buffer(a, typ, rs) { buffer =>
        q"""
        if ($buffer.size == 0){
          $success = false
          ${pushError(prettyPrint(a) + " reduceRight failed", pos)}
        }
        else {
         $success = true
         ${rs.assignNew(q"$buffer.reduceRight[$typ]($f)", typ)}
        }
      """
    }
  }

}
