package fastparsers.framework.ruleprocessing

import fastparsers.tools.TreeTools
import fastparsers.input.ParseInput
import scala.collection.mutable.HashMap
import fastparsers.parsers.Parser
import fastparsers.framework._
import fastparsers.framework.parseresult._
import fastparsers.framework.implementations.FinalFastParserImpl

/**
 * Trait used to Transform rules so that calls to other rules and foreign calls are correctly handled
 */
trait RulesTransformer extends MapRules { self: TreeTools with ParseInput =>

  import c.universe._
  import c.universe.internal._
  //import c.internal.decorators._  doesn't wokr ?

  var anonymousNumber = 0
  def getAnonymousName = {anonymousNumber += 1; "anonymous$" + anonymousNumber}

  override def process(rules: HashMap[String, RuleInfo]) = {
    transformRules(super.process(rules))
  }

  def transformRules(rulesMap: HashMap[String, RuleInfo]) = {
    val expandedRulesMap = new HashMap[String, RuleInfo]()

    def transformRule(name: String){
      val rule = rulesMap(name)
      val newRuleCode = transformRuleCalls(rule.code, rule, rulesMap, expandedRulesMap, List(name)) //c.typecheck(rule.code)
      expandedRulesMap += (name -> rule.copy(code = newRuleCode))
    }

    for (k <- rulesMap.keys)
      transformRule(k)
    expandedRulesMap
  }

  def getValidRuleInfo(ruleName: TermName,
                       rulesMap: HashMap[String, RuleInfo],
                       typeArgs: List[c.Type],
                       args: List[c.Tree]): Option[RuleInfo] =
    rulesMap.get(ruleName.toString).collect[RuleInfo]{
      case rule @ RuleInfo(_, _, params, _,_) if (params.size == args.size) => rule
      case _ => c.abort(c.enclosingPosition,"incorrect call for rule " + ruleName)
    }



 /**
  * Traverse the rule tree and expand the rule when it can
  */
 def transformRuleCalls(tree: c.Tree,
                    enclosingRule: RuleInfo,
                    rulesMap: HashMap[String, RuleInfo],
                    expandedRules: HashMap[String, RuleInfo],
                    rulesPath: List[String]): c.Tree = {

   //same hack as for annotation stuff
   def convertParsersArgs(params: List[c.Tree]) = params.map{ p =>
     getInnerTypeOf[Parser[_]](p.tpe) match {
       case Some(List(innerType)) => p match {
         case q"${ruleName : TermName}" =>
           /*c.abort(c.enclosingPosition,show(tq"($inputType,Int) => fastparsers.framework.parseresult.ParseResult[$innerType, $errorType]".tpe))
           c.untypecheck(setSymbol(p, NoSymbol)) */
           //c.untypecheck(setSymbol(p, NoSymbol))
           q"paramRule(${ruleName.toString})"
         case _ => //then need to create another anonymous rule
           val newCode = transformRuleCalls(p,enclosingRule, rulesMap, expandedRules, rulesPath)
           val newRule = RuleInfo(innerType,newCode,enclosingRule.params, enclosingRule.typeParams,newCode)
           val name = getAnonymousName
           val paramNames = enclosingRule.params.map{case ValDef(_,name,_,_) => name.toString}
           expandedRules += name -> newRule
           //setSymbol(q"${TermName(name)}", NoSymbol)
           q"paramRule($name, ..$paramNames)"
       }
       case None => p
     }
   }

   def expandRuleCall(ruleName: TermName, typeArgs: List[c.Type], args: List[c.Tree]): Option[c.Tree] =
     getValidRuleInfo(ruleName,rulesMap, typeArgs, args).collect[c.Tree] {
       case RuleInfo(typ, _, _, _,_) => 
       q"call[${typ}](${ruleName.toString}, ..${convertParsersArgs(args)})"
   }

   def expandParamCall(paramName: TermName, typeArgs: List[c.Type], args: List[c.Tree]) : Option[c.Tree] =
     getParserParams(enclosingRule.params).find(_._1 == paramName) match {
     case Some((_, tpe)) =>
       Some(q"callParam[${tpe}](${paramName.toString}, ..$args)")//TODO check for param errors
     case None =>  None
   }



   def substituteCallByForeignCall(obj: c.Tree, in: c.Tree) = new Transformer {
     override def transform(tree: c.Tree): c.Tree = tree match {
       case q"$_.call[$t](..$a)" => q"foreignCall[$t]($obj,..$a)"
       case _ => super.transform(tree)
     }
   }.transform(in)
   
   def expandForeignCall(obj: c.Tree, ruleName: TermName, args: List[c.Tree]) =
    c.typecheck(obj).tpe.members.find(x => x.name == ruleName) match {
      case Some(rule) => rule.typeSignature.resultType match {
        case AnnotatedType(annotations,typ) => annotations.find(_.tree.tpe =:= typeOf[saveAST]) match {
          case Some(annot) =>
            val codetyp = getInnerTypeOf[ParseResult[_,_]](typ) getOrElse c.abort(c.enclosingPosition, "wrong type for " + show(typ) + " during foreign call expansion")
            val concatName = obj.toString + "." + ruleName.toString
            val params = rule.typeSignature.paramLists.head//cool this is for curried things i guess, really cool
            val ruleParams = params.slice(1,params.size - 1)
            if (ruleParams.size != args.size)
              c.abort(c.enclosingPosition,"not enough parameters for rule " + concatName + " : " + show(params) + " : " + show(args))
            //same logic as in normal rule call
            if (!rulesPath.contains(concatName)) { //never supposed to be false... for now
              //we have to substitute parameters AND other rule calls
              val code = callToTermName(annot.tree.children(1))//transformRuleCalls(annot.tree.children(1),enclosingRule,rulesMap, expandedRules,concatName::rulesPath)
              val substituedParams = subsituteParams2(ruleParams.map(_.name.toTermName),args, code)
              val tmp = transformRuleCalls(substituedParams, enclosingRule, rulesMap, expandedRules, rulesPath)
              val substitued = substituteCallByForeignCall(obj, tmp)
              //c.abort(c.enclosingPosition,show(substitued))
              //c.abort(c.enclosingPosition, show(code))
              q"compound[${codetyp.head}]($substitued)" //this suppose that the code is already expanded, maybe everything would be easier if it wasn't...
            }
            else
              q"foreignCall[$codetyp]($obj,${ruleName.toString},..${convertParsersArgs(args)})"
          case _ => c.abort(c.enclosingPosition,"unexpected behaviour during foreign call expansion") //TODO
        }
        case typ => c.abort(c.enclosingPosition,"error : " + show(rule.typeSignature) + " should be an annotated type")
      }
      case _ => c.abort(c.enclosingPosition, show(ruleName) + " does not exists in" + show(obj))  //should never happend actually  because wouldnt compile
    }

   tree match {
     case q"if($c) $a else $b" =>
       q"if($c) ${transformRuleCalls(a, enclosingRule,rulesMap,expandedRules,rulesPath)} else ${transformRuleCalls(b, enclosingRule,rulesMap,expandedRules,rulesPath)}"
     case q"$a.${b: TermName}(..$args)" if a.tpe <:< typeOf[FinalFastParserImpl] =>  //TODO put comments
       expandForeignCall(a,b,args)
     case q"$a.${b: TermName}" if a.tpe <:< typeOf[FinalFastParserImpl] =>  //TODO put comments
       expandForeignCall(a,b,Nil)
     case q"$a.$m[..$d](..$b)" =>
        val callee = transformRuleCalls(a, enclosingRule, rulesMap,expandedRules, rulesPath)
        val args = b.map(transformRuleCalls(_, enclosingRule, rulesMap,expandedRules, rulesPath))
        q"$callee.$m[..$d](..$args)"
     case q"${ruleName: TermName}[..$t](..$args)" =>
        expandParamCall(ruleName, t.map(_.tpe), args) getOrElse (expandRuleCall(ruleName, t.map(_.tpe),args) getOrElse tree)
     case q"${ruleName: TermName}(..$args)" =>
        expandRuleCall(ruleName, Nil,args) getOrElse tree
     case q"$f[..$d](..$b)" =>
        val callee = transformRuleCalls(f, enclosingRule, rulesMap,expandedRules, rulesPath) //because of repFold and al curried stuff
        val args = b.map(transformRuleCalls(_, enclosingRule, rulesMap,expandedRules, rulesPath))
        q"$callee[..$d](..$args)"
     case q"$a.${f: TermName}" =>
        val callee = transformRuleCalls(a, enclosingRule, rulesMap,expandedRules, rulesPath)
        q"$callee.$f"
     case q"${ruleName: TermName}[..$t]" =>
       expandParamCall(ruleName, t.map(_.tpe), Nil) getOrElse (expandRuleCall(ruleName, t.map(_.tpe),Nil) getOrElse tree)
     case q"${ruleName: TermName}" =>
       expandParamCall(ruleName, Nil, Nil) getOrElse (expandRuleCall(ruleName, Nil,Nil) getOrElse tree)
     case _ => tree
   }
 }
}