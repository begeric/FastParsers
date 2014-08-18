package fastparsers.parsers

import fastparsers.input.StringLikeInput
import fastparsers.error.ParseError

/**
 * Created by Eric on 22.04.14.
 * Implementation of TokenParsers
 */
trait TokenParsersImpl extends ParserImplBase { self: StringLikeInput  with ParseError =>

  import c.universe._

  override def expand(tree: c.Tree, rs: ResultsStruct) = tree match {
    case q"$_.lit($str)"     => parseLit(str, rs)
    case q"$_.ident"         => parseIdentifier(rs)
    case q"$_.stringLit"     => parseStringLit(rs)
    case q"$_.number"        => parseNumber(rs)
    case q"$_.decimalNumber" => parseDecimalNumber(rs)
    case q"$_.whitespaces"   => parseWhiteSpaces(rs)
    case _                            => super.expand(tree, rs)
  }

  override def prettyPrint(tree: c.Tree) = tree match {
    case q"$_.lit($str)"     => "lit(" + show(str) + ")"
    case q"$_.ident"         => "ident"
    case q"$_.stringLit"     => "stringLit"
    case q"$_.number"        => "number"
    case q"$_.decimalNumber" => "decimalNumber"
    case q"$_.whitespaces"   => "whitespaces"
    case _                            => super.prettyPrint(tree)
  }

  private def skipWhiteSpace = {
    q"""
    while($isNEOI && ($currentInput == ' ' || $currentInput == '\t' || $currentInput == '\n' || $currentInput == '\r'))
      $advance
    """
  }

  private def parseLit(str: c.Tree, rs: ResultsStruct) = {
    val tmpstr = TermName(c.freshName)
    val litsize = TermName(c.freshName)
    val i = TermName(c.freshName)
    //error = "`" + $str + "' expected but " + (if ($isEOI) "EOF" else $currentInput) + " found at " + $pos
    mark { rollback =>
     q"""
      var $i = 0
      val $litsize = $str.length
      $skipWhiteSpace
      while ($isNEOI && $i < $litsize && $currentInput == $str.charAt($i)){
        $i = $i + 1
        $advance
      }
      if ($i == $litsize){
        $success = true
        ${rs.assignNew(str, inputType)}
      }
      else {
        $success = false
        ${pushError("`" + show(str) + "' expected but ... found", pos)}
        $rollback
      }
    """
    }
  }

  private def parseIdentifier(rs: ResultsStruct) = {
    val beginpos = TermName(c.freshName)
    mark { rollback =>
     q"""
      $skipWhiteSpace
      val $beginpos = $pos
      if ($isNEOI && Character.isJavaIdentifierStart($currentInput)){
        $advance
        while ($isNEOI && Character.isJavaIdentifierPart($currentInput)) {
          $advance
        }
        ${rs.assignNew(getInputWindow(q"$beginpos", q"$pos"), inputWindowType)}
        $success = true
      }
      else {
        $rollback
        $success = false
      }
      """
    }
  }

  private def parseStringLit(rs: ResultsStruct) = {
    val beginpos = TermName(c.freshName)
    mark { rollback =>
     q"""
      $skipWhiteSpace
      val $beginpos = $pos
      if ($isNEOI && $currentInput == '\"'){
        $advance
        while ($isNEOI && $currentInput != '\"'){
          if ($currentInput == '\\'){
            $advance
          }
          $advance
        }

        if ($isNEOI) {
          $success = true
          $advance
          ${rs.assignNew(getInputWindow(q"$beginpos", q"$pos"), inputWindowType)}
        }
        else {
          $success = false
          ${pushError("expected '\"' got EOF", pos)}
          $rollback
        }
      }
      else {
        $success = false
        ${pushError("expected '\"' got EOF", pos)}
        $rollback
      }
    """
    }
  }


  private def parseNumber(rs: ResultsStruct) = {
    val beginpos = TermName(c.freshName)
    mark {rollback =>
     q"""
      $skipWhiteSpace
      val $beginpos = $pos
      if ($isNEOI && $currentInput == '-'){
        $advance
      }
      if ($isNEOI && $currentInput >= '0' && $currentInput <= '9') {
        $advance
        while ($isNEOI && $currentInput >= '0' && $currentInput <= '9'){
          $advance
        }
        $success = true
        ${rs.assignNew(getInputWindow(q"$beginpos", q"$pos"), inputWindowType)}
      }
      else {
        $success = false
        ${pushError("expected '\"' got EOF", pos)}
        $rollback
      }
    """
    }
  }

  private def parseDecimalNumber(rs: ResultsStruct) = {
    val isNeg = TermName(c.freshName)
    val beginPos = TermName(c.freshName)
    val result = rs.newVar(tq"$inputWindowType")
    mark {  rollback =>
     q"""
      $skipWhiteSpace
      var $isNeg = false
      val $beginPos = $pos
      $success = false
      if ($isNEOI && $currentInput == '-'){
        $advance
      }
      if ($isNEOI && $currentInput >= '0' && $currentInput <= '9') {
         $advance
         while ($isNEOI && $currentInput >= '0' && $currentInput <= '9')
          $advance
         if ($isNEOI && $currentInput == '.') {
            $advance
            while ($isNEOI && $currentInput >= '0' && $currentInput <= '9')
              $advance
         }
         $success = true
         ${rs.assignTo(result, getInputWindow(q"$beginPos", q"$pos"))}
      }
      else if ($isNEOI && $currentInput == '.')  {
        $advance
        if ($isNEOI && $currentInput >= '0' && $currentInput <= '9') {
          $advance
          while ($isNEOI && $currentInput >= '0' && $currentInput <= '9')
            $advance
          $success = true
         ${rs.assignTo(result, getInputWindow(q"$beginPos", q"$pos"))}
        }
      }

      """
    }
  }

  private def parseWhiteSpaces(rs: ResultsStruct) = {
    val beginpos = TermName(c.freshName)
    val result = TermName(c.freshName)
    q"""
      val $beginpos = $pos
      $skipWhiteSpace
      ${rs.assignNew(getInputWindow(q"$beginpos", q"$pos"), inputWindowType)}
      $success = true
    """
  }
}
