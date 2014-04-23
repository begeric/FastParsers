package fastparsers.tools

import scala.reflect.macros.whitebox.Context
import fastparsers.parsers.Parser

/**
  * Created by Eric on 21.04.14.
  */
trait TreeTools {
   val c: Context
   import c.universe._

  def substituteSymbol(symbol: Symbol, value: c.Tree => c.Tree, in: c.Tree) = new Transformer {
     override def transform(tree: c.Tree): c.Tree =
       if (tree.symbol == symbol) value(tree)
       else  super.transform(tree)
   }.transform(in)

   def substituteTermName(name: TermName, value: c.Tree => c.Tree, in: c.Tree) = new Transformer {
     override def transform(tree: c.Tree): c.Tree = tree match {
       case q"${treeName: TermName}" if treeName == name => value(tree)
       case q"$m val ${treeName: TermName}:$t = $v" if treeName == name => tree
       case _ => super.transform(tree)
     }
   }.transform(in)

   def subsituteParams(params: List[Symbol], args: List[c.Tree], in: c.Tree) = {
     assert(params.size == args.size)
     params.zip(args).foldLeft(in){(acc,c) => substituteSymbol(c._1, _ => c._2,acc)}
   }

   def subsituteParams2(params: List[TermName], args: List[c.Tree], in: c.Tree) = {
     assert(params.size == args.size)
     params.zip(args).foldLeft(in){(acc,c) => substituteTermName(c._1, _ => c._2,acc)}
   }

   def getInnerTypeOf[T : TypeTag](typ: c.Type): Option[List[c.Type]] = typ match {
     case TypeRef(_, _, Nil) => None
     case TypeRef(_, _, z) if typ <:< typeOf[T] => Some(z)
     case _ => None
   }

   def getParserParams(params: List[c.Tree]) =
     params.map{case ValDef(_,name,tpt,_) => (name,tpt.tpe)}
       .filter(_._2 <:< typeOf[Parser[_]])
       .map(x => (x._1,getInnerTypeOf[Parser[_]](x._2).get.head)) //TODO what do you mean its super ugly ?
 }