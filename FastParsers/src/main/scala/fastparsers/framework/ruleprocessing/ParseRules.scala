package fastparsers.framework.ruleprocessing

import fastparsers.error._
import fastparsers.input.ParseInput
import fastparsers.parsers.{Parser, ParserImplBase}
import fastparsers.tools.TreeTools
import scala.collection.mutable.{ListBuffer, HashMap}

/**
* Create the "final" code for each rule
*/
trait ParseRules extends MapRules {
  self: ParseInput with ParserImplBase with ParseError with TreeTools =>

  import c.universe._
  import c.universe.internal._
  //import c.internal.decorators._

  override def process(rules: HashMap[String, RuleInfo]) = {
   val rulesMap = super.process(rules)

   val map = new HashMap[String, RuleInfo]()
   for (k <- rulesMap.keys) {
     val rule = rulesMap(k)
     map += ((k, rule.copy(code = createRuleDef(k, rule))))
   }
   map
 }

  def convertParsersParams(params: List[c.Tree]) = params.map{
    case valdef @ ValDef(m,n,t,v)  => getInnerTypeOf[Parser[_]](t.tpe) match {
      case Some(List(innerType)) =>
        ValDef(m,n,tq"($inputType, Int) => fastparsers.framework.parseresult.ParseResult[$innerType, $errorType]",v)
      case None => valdef
    }
  }

  def removeCompileTimeAnnotation(tree: c.Tree): c.Tree = new Transformer {
    override def transform(tree: c.Tree): c.Tree = tree match {
      case orig @ Select(qual, name) if orig.symbol.annotations.exists(_.tree.tpe =:= typeOf[scala.annotation.compileTimeOnly]) =>
        val newStuff = setType(setSymbol(Select(qual, name),NoSymbol), orig.tpe)
        super.transform(newStuff)
      case _ => super.transform(tree)
    }
  }.transform(c.typecheck(callToString(tree)))

  private def createRuleDef(name: String, rule: RuleInfo): c.Tree = {
   val ruleName = TermName(name)
   val startPosition = TermName(c.freshName)
   val rs = new ResultsStruct(new ListBuffer[Result]())
   val ruleCode = expand(rule.code, rs)
   val initResults = rs.results.map(x => q"var ${x._1}:${x._2} = ${zeroValue(x._2)}")
   val tupledResults = rs.combine

   val result = q"""fastparsers.framework.parseresult.ParseResult($success,error,if ($success) $tupledResults else ${zeroValue(tq"${rule.typ}")},$pos)"""

   val wrapCode =
     q"""
     var $success = false
       ..$initResults
       $ruleCode
       $result
    """

   val code = initError(initInput(q"$startPosition", wrapCode))

   val rewriteParams = convertParsersParams(rule.params)

   val replacedTree = removeCompileTimeAnnotation(rule.code)// @saveAST(${replacedTree})
   // c.abort(c.enclosingPosition, show(code))
   val allParams = q"$inputValue: $inputType" :: (rewriteParams :+ q"val $startPosition: Int = 0")
   val rulecode = c.untypecheck(
     q" def $ruleName[..${rule.typeParams}](..$allParams):fastparsers.framework.parseresult.ParseResult[${rule.typ}, $errorType] = $code" ) match {
     case q"def $a[$t](..$b):$d = $e" => q"def $a[$t](..$b):$d  @fastparsers.framework.saveAST(${replacedTree}) = $e"
     case q"def $a(..$b):$d = $e" => q"def $a(..$b):$d @fastparsers.framework.saveAST(${replacedTree})  = $e"
   }   //TODO o/w typecheck error. explain. This is retarded  Proxy for x error
    rulecode
 }

}