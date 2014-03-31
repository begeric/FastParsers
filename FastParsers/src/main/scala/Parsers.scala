import scala.annotation.compileTimeOnly
import scala.collection.mutable.ListBuffer
import scala.reflect.macros.whitebox.Context

/**
 * Façade of a Parser. It only appears in the tree given to the macro in order to be expanded
 */
trait Parser[+T]


/**
 *  Provide the interface and the basics method needed to implement the transformation on parsers
 */
trait CombinatorImpl { self:ParseInput =>
  val c:Context
  import c.universe._


  type Result = (TermName,c.Tree,Boolean)

  /**
   * Structure to deal with the results of a rule expansion
   */
  class ResultsStruct(var results:ListBuffer[Result]){
    def this() = this(new ListBuffer[Result]())
    def setNoUse = results = results.map(x => (x._1,x._2,false))
    def append(r:Result) = results.append(r)
    def append(rs:ResultsStruct) = results.appendAll(rs.results)
  }

  /**
   * Combine a list of results into either a tuple of result or into the same result
   * @param results
   * @return
   */
  def combineResults(results:ListBuffer[Result]):c.Tree = {
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

  def combineResults(rs:ResultsStruct):c.Tree = combineResults(rs.results)

  /**
   * Get the "zero" value of a certain type
   * @param typ
   * @return
   */

  def zeroValue(typ:c.Tree):c.Tree = {
    def fromString(str:String) = str match {
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
      case AppliedTypeTree(Ident(TypeName("List")),_) =>  q"Nil"
      case x => fromString(x.toString)
    }
  }


  def expand(tree:c.Tree,r:ResultsStruct):c.Tree = c.abort(c.enclosingPosition,"Not implemented combinator")
}


/**
 * Base Parsers
 * @tparam Elem
 * @tparam Input
 */
trait BaseParsers[Elem,Input] {

  object ~ {
    def unapply[T,U](x:Tuple2[T,U]):Option[Tuple2[T,U]] = Some(Tuple2(x._1,x._2))
  }

  @compileTimeOnly("can’t be used outside FastParser")
  implicit def toElem(elem:Elem):Parser[Elem] = ???
  @compileTimeOnly("can’t be used outside FastParser")
  def range(a:Elem,b:Elem):Parser[Elem] = ???
  @compileTimeOnly("can’t be used outside FastParser")
  def acceptIf(f:Elem => Boolean):Parser[Elem]  = ???
  @compileTimeOnly("can’t be used outside FastParser")
  def wildcard:Parser[Elem]  = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def guard[T](p:Parser[T]):Parser[T] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def phrase[T](p:Parser[T]):Parser[T] = ???

  trait BaseParser[T] {
    @compileTimeOnly("can’t be used outside FastParser")
    def ~[U](p2:Parser[U]):Parser[(T,U)] =    ???
    @compileTimeOnly("can’t be used outside FastParser")
    def ~>[U](p2:Parser[U]):Parser[U] =    ???
    @compileTimeOnly("can’t be used outside FastParser")
    def <~[U](p2:Parser[U]):Parser[T] =    ???
    @compileTimeOnly("can’t be used outside FastParser")
    def ||[U >: T](p2:Parser[U]):Parser[U] =  ???
    @compileTimeOnly("can’t be used outside FastParser")
    def |[U >: T](p2:Parser[U]):Parser[U] =  ???
    @compileTimeOnly("can’t be used outside FastParser")
    def ^^[U](f:T => U):Parser[U] = ???
    @compileTimeOnly("can’t be used outside FastParser")
    def map[U](f:T => U):Parser[U] = ???
    @compileTimeOnly("can’t be used outside FastParser")
    def ^^^[U](f:U):Parser[U] = ???
    @compileTimeOnly("can’t be used outside FastParser")
    def filter[U >: T](f:T => Boolean):Parser[T] = ???
    @compileTimeOnly("can’t be used outside FastParser")
    def withFailureMessage(msg:String):Parser[T] = ???
  }

  implicit class elemParser(p1:Elem) extends BaseParser[Elem]
  implicit class baseParsers[T](p1:Parser[T]) extends BaseParser[T]
}


/**
 * Expansion of Basic combinators
 */
trait BaseParsersImpl extends CombinatorImpl { self:ParseInput =>

  import c.universe._

  override def expand(tree:c.Tree,rs:ResultsStruct) = tree match{
    case q"FastParsers.baseParsers[$d]($a)" => expand(a,rs)
    case q"FastParsers.toElem($elem)" => parseElem(elem,rs)//q"success = false"
    case q"FastParsers.elemParser($elem)" => parseElem(elem,rs)
    case q"FastParsers.range($a,$b)" => parseRange(a,b,rs)
    case q"FastParsers.acceptIf($f)" => parseAcceptIf(f,rs)
    case q"FastParsers.wildcard" => parseWildcard(rs)
    case q"FastParsers.guard[$d]($a)" => parseGuard(a,d,rs)
    case q"FastParsers.phrase[$d]($a)" => parsePhrase(a,rs)
    case q"$a ~[$d] $b" => parseThen(a,b,rs)
    case q"$a ~>[$d] $b" => parseIgnoreLeft(a,b,d,rs)
    case q"$a <~[$d] $b" => parseIgnoreRight(a,b,d,rs)
    case q"$a ||[$d] $b" => parseOr(a,b,d,rs)
    case q"$a |[$d] $b" => parseOr(a,b,d,rs)
    case q"$a ^^[$d] $f" => parseMap(a,f,d,rs)
    case q"$a map[$d] $f" => parseMap(a,f,d,rs)
    case q"$a ^^^[$d] $v" => parseValue(a,v,d,rs)
    case q"$a filter[$d] $f" => parseFilter(a,f,d,rs)
    case q"$a withFailureMessage $msg" => parseWithFailureMessage(a,msg,rs)
    case q"call[$d](${ruleCall : TermName})" => parseRuleCall(ruleCall,d,rs)
    case q"compound[$d]($a)" => parseCompound(a,d,rs)
    case _ => super.expand(tree,rs)//q"""println(show(reify($tree).tree))"""
  }


  private def parseElem(a:c.Tree,rs:ResultsStruct):c.Tree = {
    val result = TermName(c.freshName)
    rs.append((result,inputElemType,true))
    q"""
      if ($isNEOI && $currentInput == $a){
        $result = $a
        $advance
        success = true
       }
       else {
          success = false
          msg = "expected '" + $a + " at " + $pos
        }
     """
  }

  private def parseRange(a:c.Tree,b:c.Tree,rs:ResultsStruct):c.Tree = {
    val result = TermName(c.freshName)
    rs.append((result,inputElemType,true))
    q"""
     if ($isNEOI && $currentInput >= $a && $currentInput <= $b){
      $result = $currentInput
      $advance
      success = true
     }
     else {
        success = false
        msg = "expected in range ('" + $a + "', '" + $b + "')  at " + $pos
     }
    """
  }

  private def parseAcceptIf(f:c.Tree,rs:ResultsStruct):c.Tree = {
    val result = TermName(c.freshName)
    rs.append((result,inputElemType,true))
    q"""
     if ($isNEOI && $f($currentInput)){
      $result = $currentInput
      $advance
      success = true
     }
     else {
        success = false
        msg = "acceptIf combinator failed at " + $pos
     }
     """
  }

  private def parseWildcard(rs:ResultsStruct):c.Tree = {
    val result = TermName(c.freshName)
    rs.append((result,inputElemType,true))
    q"""
      $result = $currentInput
      $advance
      success = true
    """
  }

  private def parseGuard(a:c.Tree, typ:c.Tree, rs:ResultsStruct):c.Tree = {
    val tree = mark{rollback =>
      q"""
         ${expand(a,rs)}
         ${rollback}
       """
    }
    tree
  }

  private def parsePhrase(a:c.Tree, rs:ResultsStruct):c.Tree = {
    q"""
    ${expand(a,rs)}
    if (success) {
      if (!$isEOI){
        success = false
        msg = "not all the input is consummed, at pos " + $pos
      }
    }
    """
  }

  private def parseThen(a:c.Tree,b:c.Tree,rs:ResultsStruct): c.Tree = {
    q"""
      ${expand(a,rs)}
      if (success) {
        ${expand(b,rs)}
      }
   """
  }

  private def parseIgnoreLeft(a:c.Tree,b:c.Tree,typ:c.Tree,rs:ResultsStruct): c.Tree = {
    val results_tmp = new ResultsStruct()
    val tree =
    q"""
      ${expand(a,results_tmp)}
      if (success) {
        ${expand(b,rs)}
      }
     """
    results_tmp.setNoUse
    rs.append(results_tmp)
    tree
  }

  private def parseIgnoreRight(a:c.Tree,b:c.Tree,typ:c.Tree,rs:ResultsStruct): c.Tree = {
    val results_tmp = new ResultsStruct()
    val tree =
    q"""
      ${expand(a,rs)}
      if (success) {
        ${expand(b,results_tmp)}
      }
     """
    results_tmp.setNoUse
    rs.append(results_tmp)
    tree
  }

  def parseOr(a:c.Tree,b:c.Tree,typ:c.Tree,rs:ResultsStruct): c.Tree = {
    val result = TermName(c.freshName)
    var results_tmp1 = new ResultsStruct()
    var results_tmp2 = new ResultsStruct()
    val tree = mark {rollback =>
      q"""
          ${expand(a,results_tmp1)}
          if (!success) {
            ${rollback}
            ${expand(b,results_tmp2)}
            if (success)
              $result = ${combineResults(results_tmp2)}
          }
          else {
            $result = ${combineResults(results_tmp1)}
          }
        """
    }

    results_tmp1.setNoUse
    results_tmp2.setNoUse
    rs.append((result,typ,true))   //tq"typ"    Ident(TypeName("Any"))
    rs.append(results_tmp1)
    rs.append(results_tmp2)
    tree
  }

  private def parseMap(a:c.Tree,f:c.Tree,typ:c.Tree,rs:ResultsStruct):c.Tree = {
    val result = TermName(c.freshName)
    val tmp_f = TermName(c.freshName)
    val results_tmp = new ResultsStruct()
    val tree =
    q"""
      val $tmp_f = $f
      ${expand(a,results_tmp)}
       if (success)
         $result = $tmp_f(${combineResults(results_tmp)})
      """
    results_tmp.setNoUse
    rs.append(results_tmp)
    rs.append((result,typ,true))
    tree
  }

  private def parseValue(a:c.Tree,v:c.Tree,typ:c.Tree,rs:ResultsStruct):c.Tree = {
    val result = TermName(c.freshName)
    val results_tmp = new ResultsStruct()
    val tree =
      q"""
      ${expand(a,results_tmp)}
       if (success)
         $result = $v
      """
    results_tmp.setNoUse
    rs.append(results_tmp)
    rs.append((result,typ,true))
    tree
  }

  private def parseFilter(a:c.Tree,f:c.Tree,typ:c.Tree,rs:ResultsStruct):c.Tree = {
    val result = TermName(c.freshName)
    val tmp_f = TermName(c.freshName)
    val results_tmp = new ResultsStruct()
    val tree = mark{rollback =>
    q"""
      val $tmp_f = $f
      ${expand(a,results_tmp)}
       if (success && $tmp_f(${combineResults(results_tmp)}))
         $result = ${combineResults(results_tmp)}
       else {
        success = false
        msg = "incorrect result for 'rule' at filter('rule') at " + ${pos}
        $rollback
       }
      """
    }
    results_tmp.setNoUse
    rs.append(results_tmp)
    rs.append((result,typ,true))
    tree
  }

  private def parseWithFailureMessage(a:c.Tree,msg:c.Tree,rs:ResultsStruct):c.Tree = {
    q"""
     ${expand(a,rs)}
      if (!success)
         msg = $msg
    """
  }

  private def parseRuleCall(ruleCall:TermName,typ:c.Tree,rs:ResultsStruct): c.Tree = {
    val callResult = TermName(c.freshName)
    val result = TermName(c.freshName)

    val tree = q"""
        val $callResult = ${ruleCall}(input.substring($offset))
        success = $callResult.success
        if (success){
          ${advanceTo(q"$callResult.inputPos")}
          $result = $callResult.result
         }
        else
          msg = $callResult.msg
        """
    rs.append((result,typ,true))
    tree
  }

  private def parseCompound(a:c.Tree,typ:c.Tree,rs:ResultsStruct):c.Tree = {
    val result = TermName(c.freshName)
    var results_tmp = new ResultsStruct()
    val tree =
      q"""
        ${expand(a,results_tmp)}
        $result = ${combineResults(results_tmp)}
      """
    results_tmp.setNoUse
    rs.append((result,typ,true))
    rs.append(results_tmp)
    tree
  }
}
