import scala.annotation.compileTimeOnly

trait TokenParsers {

  @compileTimeOnly("can’t be used outside FastParser")
  implicit def lit(str:String):Parser[String] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def ident:Parser[String]  = ???
  @compileTimeOnly("can’t be used outside FastParser")
  def number:Parser[Int] = ???
  @compileTimeOnly("can’t be used outside FastParser")
  def decimalNumber:Parser[Float] = ???
  @compileTimeOnly("can’t be used outside FastParser")
  def stringLit:Parser[String] = ???
  @compileTimeOnly("can’t be used outside FastParser")
  def whitespaces:Parser[String] = ???

}

trait TokenParsersImpl extends CombinatorImpl { self:StringInput =>

  import c.universe._

  override def expand(tree:c.Tree,rs:ResultsStruct) = tree match{
    case q"FastParsers.lit($str)" => parseLit(str,rs)
    case q"FastParsers.stringLit" => parseStringLit(rs)
    case q"FastParsers.number" => parseNumber(rs)
    case q"FastParsers.decimalNumber" => parseDecimalNumber(rs)
    case q"FastParsers.whitespaces" => parseWhiteSpaces(rs)
    case _ => super.expand(tree,rs)
  }

  private def skipWhiteSpace:c.Tree = {
    q"""
    while($isNEOI && ($currentInput == ' ' || $currentInput == '\t'))
      $advance
    """
  }

  private def parseLit(str:c.Tree, rs:ResultsStruct):c.Tree = {
    val result = TermName(c.freshName)
    val tmpstr = TermName(c.freshName)
    val i = TermName(c.freshName)
    rs.append((result,tq"String",true))
    mark {rollback =>
      q"""
      var $i = 0
      val $tmpstr = $str
      $skipWhiteSpace
      while ($isNEOI && $i < $str.length && $currentInput == $tmpstr.charAt($i)){
        $i = $i + 1
        $advance
      }
      if ($i == $str.length){
        success = true
        $result = $tmpstr
      }
      else {
        success = false
        msg = "`" + $str + "' expected but " + (if ($isEOI) "EOF" else $currentInput) + " found at " + $pos
        $rollback
      }
    """
    }
  }

  private def parseStringLit(rs:ResultsStruct):c.Tree = {
    val beginpos = TermName(c.freshName)
    val result = TermName(c.freshName)
    rs.append((result,tq"String",true))
    mark {rollback => q"""
      $skipWhiteSpace
      if ($isNEOI && $currentInput == '\"'){
        $advance
        val $beginpos = $pos
        while ($isNEOI && $currentInput != '\"'){
          if ($currentInput == '\\'){
            $advance
          }
          $advance
        }

        if ($isNEOI) {
          success = true
          $result = ${slice(q"$beginpos",q"$pos")}
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
  }

  private def parseNumber(rs:ResultsStruct):c.Tree = {
    val isNeg = TermName(c.freshName)
    val result = TermName(c.freshName)
    rs.append((result,tq"Int",true))
    mark {rollback =>
    q"""
      $skipWhiteSpace
      var $isNeg = false
      if ($currentInput == '-'){
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
  }

  private def parseDecimalNumber(rs:ResultsStruct):c.Tree = {
    val isNeg = TermName(c.freshName)
    val beginPos = TermName(c.freshName)
    val result = TermName(c.freshName)
    rs.append((result,tq"Float",true))
    mark {rollback =>
      q"""
      $skipWhiteSpace
      var $isNeg = false
      val $beginPos = $pos
      success = false
      if ($currentInput == '-'){
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
         $result = ${slice(q"$beginPos",q"$pos")}.toFloat
      }
      else if ($isNEOI && $currentInput == '.')  {
        $advance
        if ($isNEOI && $currentInput >= '0' && $currentInput <= '9') {
          $advance
          while ($isNEOI && $currentInput >= '0' && $currentInput <= '9')
            $advance
          success = true
          $result = ${slice(q"$beginPos",q"$pos")}.toFloat
        }
      }

      """
    }
  }

  private def parseWhiteSpaces(rs:ResultsStruct):c.Tree = {
    val beginPos = TermName(c.freshName)
    val result = TermName(c.freshName)
    rs.append((result,tq"String",true))
    q"""
      val $beginPos = $pos
      $skipWhiteSpace
      $result = ${slice(q"$beginPos",q"$pos")}
      success = true
    """
  }
}
