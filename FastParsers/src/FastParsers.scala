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
    def ^^[U](input:Parser[T] => U):Parser[T] = ???

    @compileTimeOnly("can’t be used outside FastParser")
    def rep(min:Int,max:Int):Parser[T] = ???
  }
  //implicit def toElem[T](elem:T) = Elem(elem)
  implicit def toElem(elem:Char) = Elem(elem)

  //case class Elem[T](elem:T) extends Parser[T]
  case class Elem(elem:Char) extends Parser[Char]

  trait ParseResult{}
  case class Success() extends ParseResult
  case class Failure(msg:String) extends ParseResult



  def FastParser(rules: => Unit):Any = macro FastParser_impl
  def FastParser_impl(c: Context)(rules: c.Tree)= {
    import c.universe._

    implicit val unliftPoint = Unliftable[Elem] {
      case q"_root_.FastParsers.Elem(${x : Char})" => Elem(x)
    }

    def parseRule(rule:c.Tree):c.Tree = rule match{
      case q"FastParsers.Elem.apply(...$a)" => q"""println("owi4")"""//q"""if (input.next == $a) Success() else Failure()"""
      case q"FastParsers.toElem($a)" =>
        q"""
        if (input.first == $a){
          input = input.rest
          Success()
         }
         else
          Failure("expected '" + $a + "', got '" + input.first + "' at " + input.pos)"""
      case q"$a ~ $b" =>
        val tmp = TermName(c.freshName)
       q"""
       val $tmp = (${parseRule(a)})
         $tmp match {
              case Success() => ${parseRule(b)}
              case f => f
            }"""
      case q"$a || $b" =>
        val input_tmp = TermName(c.freshName)
        val tmp = TermName(c.freshName)
        q"""
        val $input_tmp = input
            val $tmp = (${parseRule(a)})
             $tmp match {
              case Failure(_) =>
                input = $input_tmp
                ${parseRule(b)}
               case s => s
           }"""
      case q"$a.rep($min,$max)" =>
        val counter =  TermName(c.freshName)
        val tmp = TermName(c.freshName)
        val cont = TermName(c.freshName)
        q"""
          var $counter = 0
          var $cont = true
          var $tmp:ParseResult = Success()
          while($cont){
            $tmp = (${parseRule(a)})
            $tmp match {
              case Success() =>
                if ($counter >= $max)
                  $cont = false
               case Failure(_) =>
                if ($counter >= $min)
                  $tmp = Success()
                $cont = false
            }
            $counter = $counter + 1
          }
          $tmp
        """
      /*case q"FastParsers.toElem($a).?" => q"""println($a + "?")"""
      case q"FastParsers.toElem($a).+" => q"""println($a + "?")"""
      case q"FastParsers.toElem($a).*" => q"""println($a + "?")"""
      case q"${name : Ident}" =>  q"""println(${name.toString})"""   */
      case _ => q"""println(show(reify($rule)))"""
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
