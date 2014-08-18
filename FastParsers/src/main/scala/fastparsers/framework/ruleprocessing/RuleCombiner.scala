package fastparsers.framework.ruleprocessing

import scala.reflect.macros.whitebox.Context
import scala.collection.mutable.HashMap

/**
* Create the final code object
*/
trait RuleCombiner extends ReduceRules {
 val c: Context

  import c.universe._


  def combine(rules: HashMap[String, RuleInfo]) = {
   val anon = TypeName(c.freshName)
   val dmmy = TermName(c.freshName) //no joke : see http://stackoverflow.com/questions/14370842/getting-a-structural-type-with-an-anonymous-classs-methods-from-a-macro

   val methods = rules.values.map(_.code)

   val methodsEmpty = rules.keySet.map{ k =>
    val rule = rules(k)
    val ruleName = TermName(k)
    rule.params match {
      case Nil =>      q"""@scala.annotation.compileTimeOnly("can't be used outside of FastParser") def $ruleName[..${rule.typeParams}]: fastparsers.parsers.Parser[${rule.typ}] = ???"""
      case params =>   q"""@scala.annotation.compileTimeOnly("can't be used outside of FastParser") def $ruleName[..${rule.typeParams}](..${rule.params}): fastparsers.parsers.Parser[${rule.typ}] = ???"""
    }
   }

   //

   val tree = q"""
     class $anon extends fastparsers.framework.implementations.FinalFastParserImpl {
         import scala.collection.mutable.ListBuffer
         import scala.reflect.runtime.universe._
         ..$methodsEmpty
         ..$methods
     }
     val $dmmy = 0
     new $anon
   """
   //c.abort(c.enclosingPosition,show(tree))
   tree
 }
}
