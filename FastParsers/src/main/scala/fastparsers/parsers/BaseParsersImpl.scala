package fastparsers.parsers

import fastparsers.input._
import fastparsers.framework._
import fastparsers.error.ParseError

/**
 * Implementation of Basic combinators
 */
trait BaseParsersImpl extends ParserImplBase { self: ParseInput with ParseError with IgnoreResultsPolicy =>

  import c.universe._

  override def expand(tree: c.Tree, rs: ResultsStruct) = tree match {
    case q"$_.baseParsers[$d]($a)" => expand(a, rs)
    case q"$_.toElem(($a,$b))"     => parseRange(a, b, rs)
    case q"$_.toElem($elem)"       => parseElem(elem, rs)
    case q"$_.elemParser($elem)"   => parseElem(elem, rs)
    case q"$_.range($a,$b)"        => parseRange(a, b, rs)
    case q"$_.accept(..$a)"        => parseAccept(a,negate = false,rs)
    case q"$_.not(..$a)"           => parseAccept(a,negate = true,rs)
    case q"$_.acceptIf($f)"        => parseAcceptIf(f, rs)
    case q"$_.wildcard"            => parseWildcard(rs)
    case q"$_.guard[$d]($a)"       => parseGuard(a, d, rs)
    case q"$_.takeWhile($f)"       => parseTakeWhile(f, rs)
    case q"$_.takeWhile2($f)"      => parseTakeWhile2(f, rs)
    case q"$_.take($n)"            => parseTake(n, rs)
    case q"$_.raw[$d]($a)"         => parseRaw(a,rs)
    case q"$_.phrase[$d]($a)"      => parsePhrase(a, rs)
    case q"$_.failure($a)"         => parseFailure(a,rs)
    case q"$_.success[$d]($a)"     => parseSuccess(a,d,rs)
    case q"$_.position"            => parsePosition(rs)
    case q"$_.positioned[$d]($a)"  => parsePositioned(a,d,rs)
    case q"$a ~[$d] $b"                     => parseThen(a, b, rs)
    case q"$a ~>[$d] $b"                    => parseIgnoreLeft(a, b, d, rs)
    case q"$a <~[$d] $b"                    => parseIgnoreRight(a, b, d, rs)
    case q"$a ||[$d] $b"                    => parseOr(a, b, d, rs)
    case q"$a |[$d] $b"                     => parseOr(a, b, d, rs)
    case q"$a ^^[$d] $f"                    => parseMap(a, f, d, rs)
    case q"$a map[$d] $f"                   => parseMap(a, f, d, rs)
    case q"$a ^^^[$d] $v"                   => parseValue(a, v, d, rs)
    case q"$a filter[$d] $f"                => parseFilter(a, f, d, rs)
    case q"$a withFailureMessage $msg"      => parseWithFailureMessage(a, msg, rs)
    case q"call[$d](${ruleCall: String},..$params)" => parseRuleCall(q"${TermName(ruleCall)}", params, d, rs)
    case q"call[$d](${ruleCall: TermName},..$params)" => parseRuleCall(q"$ruleCall", params, d, rs)
    case q"callParam[$d](${ruleCall: String})" => parseRuleCall(q"${TermName(ruleCall)}", Nil, d, rs)
    case q"compound[$d]($a)"                => parseCompound(a, d, rs)      //TODO needed?
    case q"foreignCall[$d]($obj,${ruleCall: String},..$params)" => parseRuleCall(q"$obj.${TermName(ruleCall)}", params, d, rs)
    case q"$_.call[$d](${ruleCall: String},..$params)" => parseRuleCall(q"${TermName(ruleCall)}", params, d, rs)
    case q"$_.call[$d](${ruleCall: TermName},..$params)" => parseRuleCall(q"$ruleCall", params, d, rs)
    case q"$_.callParam[$d](${ruleCall: String})" => parseRuleCall(q"${TermName(ruleCall)}", Nil, d, rs)
    case q"$_.compound[$d]($a)"                => parseCompound(a, d, rs)
    case q"$_.foreignCall[$d]($obj,${ruleCall: String},..$params)" => parseRuleCall(q"$obj.${TermName(ruleCall)}", params, d, rs)
    case q"if ($cond) $a else $b"           =>
      c.typecheck(tree).tpe match {
        case TypeRef(_, _, List(d)) => parseIfThnEls(cond,a,b,q"$d",rs)
        case x => c.abort(c.enclosingPosition,"Ill formed if, type must be fastparsers.parsers.Parser[_] it is now : " + show(x))
      }
    case _                                  => super.expand(tree, rs) //q"""println(show(reify($tree).tree))"""
  }

  override def prettyPrint(tree: c.Tree) = tree match {
    case q"$_.baseParsers[$d]($a)" => prettyPrint(a)
    case q"$_.toElem(($a,$b))"     => "(" + a.toString + ", " + b.toString + ")"
    case q"$_.toElem($elem)"       => elem.toString
    case q"$_.elemParser($elem)"   => elem.toString
    case q"$_.accept(..$a)"        => "accept(" + a.map(prettyPrint(_)) + ")"
    case q"$_.not(..$a)"           => "not(" + a.map(prettyPrint(_)) + ")"
    case q"$_.acceptIf($f)"        => "acceptIf(" + prettyPrint(f) + ")"
    case q"$_.wildcard"            => "wildcard"
    case q"$_.guard[$d]($a)"       => "guard(" + prettyPrint(a) + ")"
    case q"$_.takeWhile($f)"       => "takeWhile(" + prettyPrint(f) + ")"
    case q"$_.take($n)"            => "take(" + show(n) + ")"
    case q"$_.raw[$d]($a)"         => "raw(" + prettyPrint(a) + ")"
    case q"$_.phrase[$d]($a)"      => "phrase(" + prettyPrint(a) + ")"
    case q"$_.failure($a)"         => "failure(" + prettyPrint(a) + ")"
    case q"$_.success[$d]($a)"     => "success(" + prettyPrint(a) + ")"
    case q"$_.position"            => "position"
    case q"$_.positioned[$d]($a)"  => "positioned(" + prettyPrint(a) + ")"
    case q"$a ~[$d] $b"                     => "(" + prettyPrint(a) + " ~ " + prettyPrint(b) + ")"
    case q"$a ~>[$d] $b"                    => "(" + prettyPrint(a) + " ~> " + prettyPrint(b) + ")"
    case q"$a <~[$d] $b"                    => "(" + prettyPrint(a) + " <~ " + prettyPrint(b) + ")"
    case q"$a ||[$d] $b"                    => prettyPrint(a) + " | " + prettyPrint(b)
    case q"$a |[$d] $b"                     => prettyPrint(a) + " | " + prettyPrint(b)
    case q"$a ^^[$d] $f"                    => prettyPrint(a) + " ^^(" + prettyPrint(f) + ")"
    case q"$a map[$d] $f"                   => prettyPrint(a) + " map (" + prettyPrint(f) + ")"
    case q"$a ^^^[$d] $v"                   => prettyPrint(a) + " ^^^ (" + prettyPrint(v) + ")"
    case q"$a filter[$d] $f"                => prettyPrint(a) + " filter (" + prettyPrint(f) + ")"
    case q"$a withFailureMessage $msg"      => prettyPrint(a) + " withFailureMessage (" + show(msg) + ")"
    case q"call[$d]($rule,..$params)"       => show(rule)
    case q"compound[$d]($a)"                => "(" + prettyPrint(a) + ")"
    case q"if ($cond) $a else $b"           => "if (" + prettyPrint(cond) + ")" + prettyPrint(a) + " else " + prettyPrint(b)
      case q"$_.toElemOrRange(($x,$y))"     => "(" + show(x) + ", " + show(y) + ")"
    case q"$_.toElemOrRange($x)"            => show(x)
    case _                                  => super.prettyPrint(tree)
  }


  private def parseElem(a: c.Tree, rs: ResultsStruct) = {
    q"""
      if ($isNEOI && $currentInput == $a){
        ${rs.assignNew(a, inputElemType)}
        $advance
        $success = true
       }
       else {
          $success = false
          ${pushError("expected '" + show(a), pos)}
        }
     """
  }

  private def parseRange(a: c.Tree, b: c.Tree, rs: ResultsStruct) = {
    q"""
     if ($isNEOI && $currentInput >= $a && $currentInput <= $b){
      ${rs.assignNew(currentInput, inputElemType)}
      $advance
      $success = true
     }
     else {
        $success = false
        ${pushError("expected in range ('" + show(a) + "', '" + show(b) + "')", pos)}
     }
    """
  }

  private def parseAccept(a: List[c.Tree],negate: Boolean,rs: ResultsStruct) = {
    val ranges = if (negate)  q"!(${getAcceptedElem(a)})"
                 else q"(${getAcceptedElem(a)})"
    q"""
      if ($isNEOI && $ranges){
        ${rs.assignNew(currentInput, inputElemType)}
        $advance
        $success = true
       }
       else {
          $success = false
          ${pushError("expected element in " + a.map(prettyPrint(_)).mkString, pos)}
        }
     """
  }

  private def getAcceptedElem(elems: List[c.Tree]) = {
    def acceptElem(elem: c.Tree) = elem match {
      case q"$_.toElemOrRange(($x,$y))"  => q"($currentInput >= $x && $currentInput <= $y)"
      case q"$_.toElemOrRange($x)"       => q"$currentInput == $x"
    }
    elems.tail.foldLeft(q"${acceptElem(elems.head)}"){(acc, c) => q"$acc || ${acceptElem(c)}"}
  }

  private def parseAcceptIf(f: c.Tree, rs: ResultsStruct) = {
    q"""
     if ($isNEOI && $f($currentInput)){
      ${rs.assignNew(currentInput, inputElemType)}
      $advance
      $success = true
     }
     else {
        $success = false
        ${pushError("acceptIf combinator failed", pos)}
     }
     """
  }

  private def parseWildcard(rs: ResultsStruct) = {
    q"""
      if ($isNEOI){
        ${rs.assignNew(currentInput, inputElemType)}
        $advance
        $success = true
      }
      else
        $success = false

    """
  }


  private def parseGuard(a: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    mark { rollback =>
        q"""
         ${expand(a, rs)}
         $rollback
       """
    }
  }


  private def parseTakeWhile(f: c.Tree, rs: ResultsStruct) = {
    val tmp_f = TermName(c.freshName)
    val beginpos = TermName(c.freshName)
    q"""
      val $tmp_f = $f
      val $beginpos = $pos
      while ($isNEOI && $tmp_f($currentInput))
        $advance
      ${rs.assignNew(slice(q"$beginpos", q"$pos"), inputType)}
      $success = true
    """
  }

  private def parseTakeWhile2(f: c.Tree, rs: ResultsStruct) = {
    val tmp_f = TermName(c.freshName)
    val beginpos = TermName(c.freshName)
    q"""
      val $tmp_f = $f
      val $beginpos = $pos
      while ($isNEOI && $tmp_f($currentInput))
        $advance
      ${rs.assignNew(getInputWindow(q"$beginpos", q"$pos"), inputWindowType)}
      $success = true
    """
  }

  private def parseTake(n: c.Tree, rs: ResultsStruct) = {
    q"""
    if ($pos + $n <= $inputsize) {
      $success = true
      ${rs.assignNew(slice(pos, q"$pos + $n"), inputType)}
    }
    else {
      $success = false
      ${pushError("take(" + show(n) + ") cannot proceed, only " + show(q"$inputsize - $pos") + " elements left", pos)}
    }
    """
  }

  private def parseRaw(a: c.Tree, rs: ResultsStruct) = {
    val beginpos = TermName(c.freshName)
    q"""
      val $beginpos = $pos
      ${expand(a,rs.temporary)}
      if ($success) {
        ${rs.assignNew(getInputWindow(q"$beginpos", q"$pos"), inputWindowType)}
      }
      else {
        error = "raw(" + ${prettyPrint(a)} +  ") failure at " + $pos
        ${pushError("raw(" + prettyPrint(a) +  ") failure", pos)}
      }
    """
  }

  private def parsePhrase(a: c.Tree, rs: ResultsStruct) = {
    q"""
    ${expand(a, rs)}
    if ($success) {
      if (!$isEOI){
        $success = false
        ${pushError("not all the input is consummed", pos)}
      }
    }
    """
  }


  private def parseFailure(a: c.Tree, rs: ResultsStruct) = {
    q"""
      $success = false
      ${pushError(show(a), pos)}
    """
  }
  private def  parseSuccess(a: c.Tree,typ: c.Tree, rs: ResultsStruct) = {
    q"""
      $success = true
      ${rs.assignNew(a,typ)}
    """
  }

  private def parsePosition(rs: ResultsStruct) = {
    rs.assignNew(pos,tq"Int")
  }

  private def parsePositioned(a: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    val results_tmp = rs.temporary
    val beginpos = TermName(c.freshName)
    val result = rs.newVar(typ)
    //TODO not sure if it works well when the results gets ignored...
    q"""
     val $beginpos = $pos
     ${expand(a,results_tmp)}
     if ($success) {
        ${rs.assignTo(result,results_tmp.combine)}
        $result setPos ${getPositionned(q"$beginpos")}
     }
     """
  }


  private def parseThen(a: c.Tree, b: c.Tree, rs: ResultsStruct) = {
    q"""
      ${expand(a, rs)}
      if ($success) {
        ${expand(b, rs)}
      }
   """
  }

  private def parseIgnoreLeft(a: c.Tree, b: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    q"""
      ${expand(a, ignoreResult(rs))}
      if ($success) {
        ${expand(b, rs)}
      }
     """
  }

  private def parseIgnoreRight(a: c.Tree, b: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    q"""
      ${expand(a, rs)}
      if ($success) {
        ${expand(b, ignoreResult(rs))}
      }
     """
  }

  def parseOr(a: c.Tree, b: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    val result = rs.newVar(typ)
    mark {rollback =>parseOrRHS(a, b, result, rollback, rs)}
  }

  private def parseOrRHS(rhs : c.Tree, lhs: c.Tree, result: TermName, rollback: c.Tree, rs: ResultsStruct): c.Tree = {
    var results_tmp1 = rs.temporary
    var results_tmp2 = rs.temporary
    val expandRHS = rhs match {
      case q"$a |[$_] $b" => parseOrRHS(rhs, lhs, result, rollback, results_tmp1)
      case q"$a ||[$_] $b" => parseOrRHS(a, b, result, rollback, results_tmp1)
      case x => q"${expand(x, results_tmp1)}"
    }
    q"""
       $expandRHS
        if (!$success) {
          $rollback
          ${expand(lhs, results_tmp2)}
          if ($success){
            ${rs.assignTo(result, results_tmp2.combine)}
          }
        }
        else {
            ${rs.assignTo(result, results_tmp1.combine)}
        }
    """
  }

  private def parseMap(a: c.Tree, f: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    val tmp_f = TermName(c.freshName)
    val results_tmp = rs.temporary
    val tree =
      q"""
      val $tmp_f = $f
      ${expand(a, results_tmp)}
       if ($success)
         ${rs.assignNew(q"$tmp_f.apply(${results_tmp.combine})", typ)}
      """
    c.untypecheck(tree)
  }

  private def parseValue(a: c.Tree, v: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    q"""
      ${expand(a, rs.temporary)}
      if ($success)
       ${rs.assignNew(v, typ)}
    """
  }

  private def parseFilter(a: c.Tree, f: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    val tmp_f = TermName(c.freshName)
    val results_tmp = rs.temporary
    val tree = mark { rollback =>
    q"""
      val $tmp_f = $f
      ${expand(a, results_tmp)}
       if ($success && $tmp_f(${results_tmp.combine}))
         ${rs.assignNew(results_tmp.combine,typ)}
       else {
        $success = false
        error = "incorrect result for " + ${prettyPrint(a)} + ".filter at " + $pos
        ${pushError("incorrect result for " + prettyPrint(a) + " filter", pos)}
        $rollback
       }
      """
    }
    c.untypecheck(tree)
  }

  private def parseWithFailureMessage(a: c.Tree, msg: c.Tree, rs: ResultsStruct) = {
    q"""
     ${expand(a, rs)}
      if (!$success)
        ${pushError(show(msg), pos)}
    """
  }

  private def parseRuleCall(ruleCall: c.Tree, params: List[c.Tree], typ: c.Tree, rs: ResultsStruct) = {
    val callResult = TermName(c.freshName)
    val call = params.map{
        case q"paramRule(${x: String})" => q"${TermName(x)}"
        case q"paramRule(${x: String}, ..${args: List[String]})" => 
          val in = TermName(c.freshName("input"))
          val offs = TermName(c.freshName("offset"))
          q"($in: $inputType, $offs: Int) => {${TermName(x)}($in, ..${args.map(TermName(_))}, $offs)}"
        case x => x
      } match {
      case Nil => q"$ruleCall($inputValue,$pos)"
      case p => q"$ruleCall($inputValue,..$p,$pos)"
    }

    val tree = q"""
        val $callResult = $call
        $success = $callResult.success
        if ($success){
          ${setpos(q"$callResult.inputPos")}
          ${rs.assignNew(q"$callResult.result",typ)}
         }
        else
          error = $callResult.error
        """
    c.untypecheck(tree)
  }

  /*private def parseRuleCall(ruleCall: TermName, params: List[c.Tree], typ: c.Tree, rs: ResultsStruct) = {
    val callResult = TermName(c.freshName)
    val call = params.map{
      case q"paramRule[$_](${x: String})" => q"${TermName(x)}"
      case x => 
      c.echo(c.enclosingPosition, show(x))
      x
      } match {
      case Nil => q"$ruleCall($inputValue,$pos)"
      case p => q"$ruleCall($inputValue,..$p,$pos)"
    }

    val tree = q"""
        val $callResult = $call
        success = $callResult.success
        if (success){
          ${setpos(q"$callResult.inputPos")}
          ${rs.assignNew(q"$callResult.result",typ)}
         }
        else
          error = $callResult.error
        """
    c.untypecheck(tree)
  }*/


  private def parseCompound(a: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    var results_tmp = rs.temporary
    q"""
      ${expand(a, results_tmp)}
      ${rs.assignNew(results_tmp.combine, typ)}
    """
  }

  private def parseIfThnEls(cond: c.Tree,a: c.Tree,b: c.Tree,typ: c.Tree, rs: ResultsStruct) = {
    val result = rs.newVar(typ)
    var results_tmp1 = rs.temporary
    var results_tmp2 = rs.temporary
    q"""
    if ($cond){
      ${expand(a,results_tmp1)}
      ${rs.assignTo(result, results_tmp1.combine)}
     }
    else {
      ${expand(b,results_tmp2)}
      ${rs.assignTo(result, results_tmp2.combine)}
     }
    """
  }
}
