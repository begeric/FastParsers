import scala.annotation.compileTimeOnly
import scala.collection.mutable.ListBuffer
import scala.reflect.macros.whitebox.Context

trait RepParsers {

  @compileTimeOnly("can’t be used outside FastParser")
  def rep[T](p: Parser[T], min: Int = 0, max: Int = -1): Parser[List[T]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def rep1[T](p: Parser[T]): Parser[List[T]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def repN[T](p: Parser[T], rep: Int): Parser[List[T]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def opt[T](p: Parser[T]): Parser[Option[T]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def repsep[T, U](p: Parser[T], sep: Parser[U]): Parser[List[T]] = ???

  @compileTimeOnly("can’t be used outside FastParser")
  def repsep1[T, U](p: Parser[T], sep: Parser[U]): Parser[List[T]] = ???

  implicit class repParser[T](p: Parser[T]) {
    @compileTimeOnly("can’t be used outside FastParser")
    def foldLeft[U](init: U, f: (U, T) => U): Parser[U] = ???

    @compileTimeOnly("can’t be used outside FastParser")
    def foldRight[U, X >: T](init: U, f: (T, U) => U): Parser[U] = ???

    @compileTimeOnly("can’t be used outside FastParser")
    def reduceLeft[U >: T](f: (U, T) => U): Parser[U] = ???

    @compileTimeOnly("can’t be used outside FastParser")
    def reduceRight[U >: T](f: (T, U) => U): Parser[U] = ???
  }

}

trait RepParsersImpl extends CombinatorImpl {
  self: ParseInput =>

  import c.universe._

  override def expand(tree: c.Tree, rs: ResultsStruct) = tree match {
    case q"FastParsers.repParser[$d]($a)" => expand(a, rs)
    case q"FastParsers.rep[$d]($a,$min,$max)" => parseRep(a, d, min, max, rs)
    case q"FastParsers.rep1[$d]($a)" => parseRep(a, d, q"1", q"-1", rs)
    case q"FastParsers.repN[$d]($a,$n)" => parseRep(a, d, n, n, rs)
    case q"FastParsers.opt[$d]($a)" => parseOpt(a, d, rs)
    case q"FastParsers.repsep[$typ,$d]($a,$b)" => parseRepsep(a, b, typ, atLeastOnce = false, rs)
    case q"FastParsers.repsep1[$typ,$d]($a,$b)" => parseRepsep(a, b, typ, atLeastOnce = true, rs)
    case q"$a foldLeft[$d]($init,$f)" => parseFoldLeft(a, init, f, d, rs)
    case q"$a foldRight[$d,$ptype]($init,$f)" => parseFoldRight(a, init, f, d, ptype, rs)
    case q"$a reduceLeft[$d]($f)" => parseReduceLeft(a, f, d, rs)
    case q"$a reduceRight[$d]($f)" => parseReduceRight(a, f, d, rs)
    case _ => super.expand(tree, rs)
  }

  private def parseRep(a: c.Tree, typ: c.Tree, min: c.Tree, max: c.Tree, rs: ResultsStruct) = {
    val counter = TermName(c.freshName)
    val cont = TermName(c.freshName)
    val result = TermName(c.freshName)
    val tmp_result = TermName(c.freshName)
    var results_tmp = new ResultsStruct()

    val innerWhileTree = mark {
      rollback =>
        q"""
          ${expand(a, results_tmp)}
          if (success) {
              $tmp_result.append(${combineResults(results_tmp)})
              if ($counter + 1 == $max) $cont = false
          }
          else {
              success = $counter >= $min
              $cont = false
              if (!success)
                msg = "expected at least " + $min + " occurence(s) for rep('rule') at " + $pos
              else
                $rollback

          }
        """
    }

    val tree = mark {
      rollback =>
        q"""
          var $counter = 0
          var $cont = true
          val $tmp_result = new ListBuffer[$typ]()
          success = $min == 0
          while($cont){
            $innerWhileTree
            $counter = $counter + 1
          }
          if (!success) {
            $rollback
          }
          else {
             $result = $tmp_result.toList
          }
        """
    }
    results_tmp.setNoUse
    rs.append((result, tq"List[$typ]", true))
    rs.append(results_tmp)
    tree
  }

  private def parseOpt(a: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    val result = TermName(c.freshName)
    var results_tmp = new ResultsStruct()
    val tree = mark {
      rollback =>
        q"""
        ${expand(a, results_tmp)}
        if (success) {
          $result = Some(${combineResults(results_tmp)})
        }
        else {
          $rollback
          $result = None
          success = true
        }
      """
    }
    results_tmp.setNoUse
    rs.append((result, tq"Option[$typ]", true))
    rs.append(results_tmp)
    tree
  }

  private def parseRepsep(a: c.Tree, sep: c.Tree, typ: c.Tree, atLeastOnce: Boolean, rs: ResultsStruct) = {
    var results_tmp = new ResultsStruct()
    var results_tmp2 = new ResultsStruct()
    val cont = TermName(c.freshName)
    val tmp_result = TermName(c.freshName)
    val result = TermName(c.freshName)

    val innertree2 = mark {
      rollback =>
        q"""
          ${expand(sep, results_tmp2)}
           if (!success) {
            $cont = false
            $rollback
           }
        """
    }

    val innertree1 = mark {
      rollback =>
        q"""
          ${expand(a, results_tmp)}
          if (success) {
             $tmp_result.append(${combineResults(results_tmp)})
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
        mark {
          rollback =>
            q"""
          if ($tmp_result.size == 0) {
            $rollback
            success = false
          }
          else {
            $result = $tmp_result.toList
            success = true
           }
        """
        }
      else {
        q"""
        $result = $tmp_result.toList
        success = true
      """
      }

    val tree =
      q"""
      var $cont = true
      val $tmp_result = new ListBuffer[$typ]()
      while($cont) {
        $innertree1
      }
      $assignSuccess
    """

    results_tmp.setNoUse
    results_tmp2.setNoUse
    rs.append(results_tmp)
    rs.append(results_tmp2)
    rs.append((result, tq"List[$typ]", true))
    tree
  }

  private def parseFoldLeft(a: c.Tree, init: c.Tree, f: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    var results_tmp = new ResultsStruct()
    val result = TermName(c.freshName)
    val cont = TermName(c.freshName)
    val tmp_f = TermName(c.freshName)

    val inner = mark {
      rollback =>
        q"""
        ${expand(a, results_tmp)}
         if (success)
           $result = $tmp_f($result,${combineResults(results_tmp)})
         else {
          $cont = false
          $rollback
         }
      """
    }
    val tree =
      q"""
      val $tmp_f = $f
      var $cont = true
      $result = $init
      while($cont){
        $inner
      }
      success = true
    """
    results_tmp.setNoUse
    rs.append(results_tmp)
    rs.append((result, typ, true))
    tree
  }

  private def buffer(a: c.Tree, typ: c.Tree, rs: ResultsStruct)(process: TermName => c.Tree) = {
    var results_tmp = new ResultsStruct()
    val cont = TermName(c.freshName)
    val buffer = TermName(c.freshName)

    val buffering = mark {
      rollback =>
        q"""
        ${expand(a, results_tmp)}
        if (success)
          $buffer.append(${combineResults(results_tmp)})
        else
          $cont = false
      """
    }

    val tree =
      q"""
     var $cont = true
     val $buffer = new ListBuffer[$typ]()
     while($cont){
       $buffering
     }

     ${process(buffer)}
    """
    results_tmp.setNoUse
    rs.append(results_tmp)
    tree
  }


  private def parseFoldRight(a: c.Tree, init: c.Tree, f: c.Tree, typ: c.Tree, parserType: c.Tree, rs: ResultsStruct) = {
    val result = TermName(c.freshName)
    val tree = buffer(a, parserType, rs) {
      buffer =>
        q"""
       $result = $buffer.foldRight[$typ]($init)($f)
       success = true
      """
    }
    rs.append((result, typ, true))
    tree
  }

  private def parseReduceLeft(a: c.Tree, f: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    var results_tmp = new ResultsStruct()
    val tree = mark {
      rollback =>
        q"""
       ${expand(a, results_tmp)}
       if (success){
          ${parseFoldLeft(a, combineResults(results_tmp), f, typ, rs)}
       }
       else {
        success = false
        msg = "reduceLeft failed"
        $rollback
       }
      """
    }
    results_tmp.setNoUse
    rs.append(results_tmp)
    tree
  }


  private def parseReduceRight(a: c.Tree, f: c.Tree, typ: c.Tree, rs: ResultsStruct) = {
    val result = TermName(c.freshName)
    val tree = buffer(a, typ, rs) {
      buffer =>
        q"""
        if ($buffer.size == 0){
          success = false
          msg = "reduceRight failed"
        }
        else {
         success = true
         $result = $buffer.reduceRight[$typ]($f)
        }
      """
    }
    rs.append((result, typ, true))
    tree
  }

}