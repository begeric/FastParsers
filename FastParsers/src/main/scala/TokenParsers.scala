import scala.annotation.compileTimeOnly
import scala.language.implicitConversions
import scala.util.parsing.input._

import InputWindow._

trait TokenParsers[StringRepr] {

  @compileTimeOnly("can’t be used outside FastParser")
  implicit def lit(str: StringRepr): Parser[StringRepr] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def ident: Parser[InputWindow[StringRepr]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def number: Parser[InputWindow[StringRepr]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def decimalNumber: Parser[InputWindow[StringRepr]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def stringLit: Parser[InputWindow[StringRepr]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def whitespaces: Parser[InputWindow[StringRepr]] = ???

}

trait TokenParsersImpl extends CombinatorImpl {
  self: StringLikeInput =>

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
    val result = TermName(c.freshName)
    val tmpstr = TermName(c.freshName)
    val inputsize = TermName(c.freshName)
    val i = TermName(c.freshName)
    rs.append((result, inputType, true))
    mark {
      rollback =>
        q"""
      var $i = 0
      val $inputsize = $str.length
      $skipWhiteSpace
      while ($isNEOI && $i < $inputsize && $currentInput == $str.charAt($i)){
        $i = $i + 1
        $advance
      }
      if ($i == $inputsize){
        success = true
        $result = $str
      }
      else {
        success = false
        msg = "`" + $str + "' expected but " + (if ($isEOI) "EOF" else $currentInput) + " found at " + $pos
        $rollback
      }
    """
    }
  }

  private def parseIdentifier(rs: ResultsStruct) = {
    val beginpos = TermName(c.freshName)
    val result = TermName(c.freshName)
    rs.append((result, inputWindowType, true))
    mark {
      rollback =>
        q"""
      $skipWhiteSpace
      val $beginpos = $pos
      if ($isNEOI && Character.isJavaIdentifierStart($currentInput)){
        $advance
        while ($isNEOI && Character.isJavaIdentifierPart($currentInput)) {
          $advance
        }
        $result = ${getInputWindow(q"$beginpos", q"$pos")}
        success = true
      }
      else {
        $rollback
        success = false
      }
      """
    }
  }

  private def parseStringLit(rs: ResultsStruct) = {
    val beginpos = TermName(c.freshName)
    val result = TermName(c.freshName)
    rs.append((result, inputWindowType, true))
    mark {
      rollback => q"""
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
          success = true
          $advance
          $result = ${getInputWindow(q"$beginpos", q"$pos")}
        }
        else {
          success = false
          msg = "expected '\"' got EOF at " + $pos
          $rollback
        }
      }
      else {
        success = false
        msg = "expected '\"' at " + $pos
        $rollback
      }
    """
    }
  }

 /* private def parseNumber(rs: ResultsStruct) = {
    val isNeg = TermName(c.freshName)
    val result = TermName(c.freshName)
    rs.append((result, tq"Int", true))
    mark {
      rollback =>
        q"""
      $skipWhiteSpace
      var $isNeg = false
      if ($isNEOI && $currentInput == '-'){
        $isNeg = true
        $advance
      }
      if ($isNEOI && $currentInput >= '0' && $currentInput <= '9') {
        $result = $currentInput.asDigit
        $advance
        while ($isNEOI && $currentInput >= '0' && $currentInput <= '9'){
          $result = $result * 10 + $currentInput.asDigit
          $advance
        }
        success = true
        if ($isNeg)
          $result = -$result
      }
      else {
        success = false
        msg = "expected integer at" + $pos
        $rollback
      }
    """
    }
  }  */

  private def parseNumber(rs: ResultsStruct) = {
    val isNeg = TermName(c.freshName)
    val result = TermName(c.freshName)
    val beginPos = TermName(c.freshName)
    rs.append((result, inputWindowType, true))
    mark {
      rollback =>
        q"""
      $skipWhiteSpace
      val $beginPos = $pos
      if ($isNEOI && $currentInput == '-'){
        $advance
      }
      if ($isNEOI && $currentInput >= '0' && $currentInput <= '9') {
        $advance
        while ($isNEOI && $currentInput >= '0' && $currentInput <= '9'){
          $advance
        }
        success = true
        $result = ${getInputWindow(q"$beginPos", q"$pos")}
      }
      else {
        success = false
        msg = "expected integer at" + $pos
        $rollback
      }
    """
    }
  }

  private def parseDecimalNumber(rs: ResultsStruct) = {
    val isNeg = TermName(c.freshName)
    val beginPos = TermName(c.freshName)
    val result = TermName(c.freshName)
    rs.append((result, inputWindowType, true))
    mark {
      rollback =>
        q"""
      $skipWhiteSpace
      var $isNeg = false
      val $beginPos = $pos
      success = false
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
         success = true
         $result = ${getInputWindow(q"$beginPos", q"$pos")}
      }
      else if ($isNEOI && $currentInput == '.')  {
        $advance
        if ($isNEOI && $currentInput >= '0' && $currentInput <= '9') {
          $advance
          while ($isNEOI && $currentInput >= '0' && $currentInput <= '9')
            $advance
          success = true
          $result = ${getInputWindow(q"$beginPos", q"$pos")}
        }
      }

      """
    }
  }

  private def parseWhiteSpaces(rs: ResultsStruct) = {
    val beginPos = TermName(c.freshName)
    val result = TermName(c.freshName)
    rs.append((result, inputWindowType, true))
    q"""
      val $beginPos = $pos
      $skipWhiteSpace
      $result = ${getInputWindow(q"$beginPos", q"$pos")}
      success = true
    """
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////

  /*private def parseWhiteSpaces2(rs: ResultsStruct) = {
    q"""
      $skipWhiteSpace
      success = true
    """
  }  */

  /*private def parseStringLit2(rs: ResultsStruct) = {
    val beginpos = TermName(c.freshName)
    val result = TermName(c.freshName)
    rs.append((result, tq"String", true))
    mark {
      rollback => q"""
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
          success = true
          $advance
          $result = new StringStruct(input,$beginpos, $pos)
        }
        else {
          success = false
          msg = "expected '\"' got EOF at " + $pos
          $rollback
        }
      }
      else {
        success = false
        msg = "expected '\"' at " + $pos
        $rollback
      }
    """
    }
  }

  private def parseStringLit3(rs: ResultsStruct) = {
    mark {
      rollback => q"""
      $skipWhiteSpace
      if ($isNEOI && $currentInput == '\"'){
        $advance
        while ($isNEOI && $currentInput != '\"'){
          if ($currentInput == '\\'){
            $advance
          }
          $advance
        }

        if ($isNEOI) {
          success = true
          $advance
        }
        else {
          success = false
          msg = "expected '\"' got EOF at " + $pos
          $rollback
        }
      }
      else {
        success = false
        msg = "expected '\"' at " + $pos
        $rollback
      }
    """
    }
  }  */
}
