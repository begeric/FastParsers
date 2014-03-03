/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */

import scala.collection.mutable._
import scala.language.experimental.macros
import scala.reflect.api.Universe
import scala.reflect.internal.annotations.compileTimeOnly
import scala.reflect.macros.whitebox.Context
import scala.collection.mutable.ListBuffer

object FastParsers {


  trait Parser[T]{
    @compileTimeOnly("can’t be used outside FastParser")
    def ~(parser2: Parser[T]):Parser[T] =  ???
    @compileTimeOnly("can’t be used outside FastParser")
    def ||(parser2: Parser[T]):Parser[T] =  ???
    @compileTimeOnly("can’t be used outside FastParser")
    def `?`:Parser[T] =  ???
    @compileTimeOnly("can’t be used outside FastParser")
    def `+`:Parser[T] =  ???
    @compileTimeOnly("can’t be used outside FastParser")
    def `*`:Parser[T] =  ???
    @compileTimeOnly("can’t be used outside FastParser")
    def apply(r:Range):Parser[T] = ???

    //HOW TO DO THAT ?
    @compileTimeOnly("can’t be used outside FastParser")
    def ^^[U](f:Product => U):Parser[U] = ???
    @compileTimeOnly("can’t be used outside FastParser")
    def map[U](f:Product => U):Parser[U] = ???

    @compileTimeOnly("can’t be used outside FastParser")
    def rep(min:Int,max:Int):Parser[T] = ???
  }

  @compileTimeOnly("can’t be used outside FastParser")
  def rep[T](p:Parser[T],min:Int = 0,max:Int = -1):Parser[T] = ???
  @compileTimeOnly("can’t be used outside FastParser")
  def rep1[T](p:Parser[T]):Parser[T] = ???
  @compileTimeOnly("can’t be used outside FastParser")
  def opt[T](p:Parser[T]):Parser[T] = ???


  @compileTimeOnly("can’t be used outside FastParser")
  def range[T](a:T,b:T):Parser[T] = ???

  implicit def toElem[T](elem:T) = Elem(elem)
  case class Elem[T](elem:T) extends Parser[T]


  case class ParseResult[+T](success:Boolean,msg:String,result:T)

  object Success {
    def unapply[T](p:ParseResult[T]):Option[T] =
      if (p.success) Some(p.result)
      else  None
  }

  object Failure {
    def unapply[T](p:ParseResult[T]):Option[String] =
      if (!p.success) Some(p.msg)
      else  None
  }

  def FastParser(rules: => Unit):Any = macro FastParser_impl
  def FastParser_impl(c: Context)(rules: c.Tree)= {
    import c.universe._

    type Result = (TermName,TypeName,Boolean)

    def parseRule(rule:c.Tree):c.Tree = {
      val results = new ListBuffer[Result]()
      val transform = parseRuleContent(rule,results)
      val initSuccess = q"var success = false"
      val initMsg = q"""var msg = "" """
      val initResults = results.map(x => q"var ${x._1}:Option[${x._2}] = None")
      val tupledResults = q"(..${results.filter(_._3).map(x => q"${x._1}.get")})"  //lol ?
      val result = q"""ParseResult(success,msg,if (success) $tupledResults else null)"""

      val tree = q"""
        $initSuccess
        $initMsg
        ..$initResults
        $transform
        $result
      """
      tree
      //q"""println(show(reify($tree)))"""
    }

    def makeTuple(results:ListBuffer[Result]):c.Tree = {
      if (results.size > 1)
          q"(..${results.filter(_._3).map(x => q"${x._1}.get")})"
      else if (results.size == 1 && results(0)._3)
          q"${results(0)._1}.get"
      else
         q""
    }

    /*def getUnionType(r1:ListBuffer[Result],r2:ListBuffer[Result]):TypeName = {
      val t1 = r1.filter(_._3)
      val t2 = r2.filter(_._3)
    } */

    def parseRep(a:c.Tree,min:c.Tree,max:c.Tree,results:ListBuffer[Result]):c.Tree = {
      val counter =  TermName(c.freshName)
      val input_tmp = TermName(c.freshName)
      val cont = TermName(c.freshName)
      var results_tmp = new ListBuffer[Result]()
      val result = TermName(c.freshName)
      var tmp_result = TermName(c.freshName)
      val tree = q"""
          var $counter = 0
          var $cont = true
          var $input_tmp = input
          var $tmp_result:List[Any] = Nil
          while($cont){
            ${parseRuleContent(a,results_tmp)}
            if (success) {
                $tmp_result = $tmp_result ++ List(${makeTuple(results_tmp)})
                if ($counter + 1 == $max)
                  $cont = false
            }
            else {
                success = $counter >= $min
                $cont = false
            }
            $counter = $counter + 1
          }
          if (success) {
            $result = Some($tmp_result)
          }
          else {
            input = $input_tmp
          }
        """
      results_tmp = results_tmp.map(x => (x._1,x._2,false))
      results.append((result,TypeName("Any"),true))
      results.appendAll(results_tmp)
      tree
    }

    def parseMap(a:c.Tree,f:c.Tree,results:ListBuffer[Result]) : c.Tree = {
      val result = TermName(c.freshName)
      val tree = q"""
          ${parseRuleContent(a,results)}
           if (success)
             $result = Some($f(${makeTuple(results)}))
        """
      val tmp = results.map(x => (x._1,x._2,false))
      results.clear()
      results.appendAll(tmp)
      results.append((result,TypeName("Any"),true))
      tree
    }


    def parseElem(a:c.Tree,d:c.Tree,results:ListBuffer[Result]):c.Tree = {
      val result = TermName(c.freshName)
      results.append((result,TypeName(d.toString),true))  //TODO check d.toString
      q"""
        if (input.first == $a){
          $result = Some(input.first)
          input = input.rest
          success = true
         }
         else {
            success = false
            msg = "expected '" + $a + "', got '" + input.first + "' at " + input.pos  }
          """
    }

    def parseRange(a:c.Tree,b:c.Tree,d:c.Tree,results:ListBuffer[Result]): c.Tree = {
      val result = TermName(c.freshName)
      results.append((result,TypeName(d.toString),true))  //TODO check d.toString
      q"""
        if (input.first >= $a && input.first <= $b){
          $result = Some(input.first)
          input = input.rest
          success = true
         }
         else {
            success = false
            msg = "expected in range ('" + $a + "', '" + $b + "'), got '" + input.first + "' at " + input.pos  }
          """
    }

    def parseThen(a:c.Tree,b:c.Tree,results:ListBuffer[Result]): c.Tree = {
      q"""
          ${parseRuleContent(a,results)}
          if (success) {
            ${parseRuleContent(b,results)}
          }
       """
    }

    def parseOr(a:c.Tree,b:c.Tree,results:ListBuffer[Result]): c.Tree = {
      val input_tmp = TermName(c.freshName)
      val result = TermName(c.freshName)
      var results_tmp1 = new ListBuffer[Result]()
      var results_tmp2 = new ListBuffer[Result]()
      val tree = q"""
          val $input_tmp = input
          ${parseRuleContent(a,results_tmp1)}
          if (!success) {
            input = $input_tmp
            ${parseRuleContent(b,results_tmp2)}
            $result = if (success) Some(${makeTuple(results_tmp2)}) else None
          }
          else {
            $result = Some(${makeTuple(results_tmp1)})
          }
        """
      results_tmp1 = results_tmp1.map(x => (x._1,x._2,false))
      results_tmp2 = results_tmp2.map(x => (x._1,x._2,false))
      results.append((result,TypeName("Any"),true))
      results.appendAll(results_tmp1)
      results.appendAll(results_tmp2)
      tree
    }

    def parseRuleCall(ruleCall:TermName,results:ListBuffer[Result]): c.Tree = {
      val callResult = TermName(c.freshName)
      val result = TermName(c.freshName)
      results.append((result,TypeName("Any"),true))
      q"""
        val $callResult:ParseResult[Any] = ${ruleCall}(input)
        success = $callResult.success
        if (success)
          $result = $callResult.result
        else
          msg = $callResult.msg
        """
    }

    def parseRuleContent(rule:c.Tree,results:ListBuffer[Result]):c.Tree = rule match{
      case q"FastParsers.toElem[$d]($a)" =>
        parseElem(a,d,results)
      case q"FastParsers.range[$d]($a,$b)" =>
        parseRange(a,b,d,results)
      case q"$a ~ $b" =>
        parseThen(a,b,results)
      case q"$a || $b" =>
        parseOr(a,b,results)
      case q"$a.rep($min,$max)" =>
        parseRep(a,min,max,results)
      case q"$a?" =>
        parseRep(a,q"0",q"1",results)
      case q"$a+" =>
        parseRep(a,q"1",q"-1",results)
      case q"$a*" =>
        parseRep(a,q"0",q"-1",results)
      case q"FastParsers.rep[$d]($a,$min,$max)" =>
        parseRep(a,min,max,results)
      case q"FastParsers.rep1[$d]($a)" =>
        parseRep(a,q"1",q"-1",results)
      case q"FastParsers.opt[$d]($a)" =>
        parseRep(a,q"0",q"1",results)
      case q"""${ruleCall : TermName}""" =>  parseRuleCall(ruleCall,results)
      case q"$a map[$d] ($f)" =>
        parseMap(a,f,results)
      case q"$a ^^[$d]($f)" =>
        parseMap(a,f,results)
      case _ => q"""println(show(reify("youhou")))"""
    }

    def createBasicStructure = {
      val rulesMap = new HashMap[String,c.Tree]()
      rules match {
        case q"{..$body}" =>
          body.foreach (_ match {
            case q"def $name:$_ = $b" =>
              val TermName(nameString) = name
              val in = (nameString, parseRule(b))
              rulesMap += in
            //case q""  =>
            case q"()" =>
            case x => c.abort(c.enclosingPosition, "body must only contain rule definition with the following form : def ruleName = body : " + x)
          })
          c.Expr(q"""println(..${rulesMap.keys})""")
        case _ =>
          c.abort(c.enclosingPosition, "ill-formed body, cannot be empty")//TODO can be empty ?
      }
      rulesMap
    }

    def replaceInRules(rulesMap : HashMap[String,c.Tree]) = {
      val map = new HashMap[String,c.Tree]()
      for (k <- rulesMap.keys)  {
        val term = TermName(k)
        val ruleCode = q"var input = i; ${rulesMap(k)}"
        //map += ((k,q"def $term(i:Reader[Char]) = println(show(reify($ruleCode)))"))
        map += ((k,q"def $term(i:Reader[Char]) = $ruleCode"))
      }
      map
    }

    def createFastParser(map : HashMap[String,c.Tree]) = {
      val anon = TypeName(c.freshName)
      val dmmy = TermName(c.freshName)//no joke : see http://stackoverflow.com/questions/14370842/getting-a-structural-type-with-an-anonymous-classs-methods-from-a-macro


      val methods = map.values
      //create the final parser object which will be used
      q"""
        class $anon {
           ..$methods
        }
        val $dmmy = 0
        new $anon
      """
    }

    val rulesMap = createBasicStructure
    val finalRulesMap = replaceInRules(rulesMap)
    val tree = createFastParser(finalRulesMap)
    c.Expr(tree)
    //c.Expr(q"""println(show(reify($tree).tree))""")
   // c.Expr(q"new {def rule1(input:String) = println(input)}")
  }
}
