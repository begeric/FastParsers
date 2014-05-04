package fastparsers.tools

import scala.reflect.macros.whitebox.Context
import fastparsers.parsers.Parser

/**
  * Created by Eric on 21.04.14.
 * Diverse tool to deal with ASTs
  */
trait TreeTools {
   val c: Context
   import c.universe._

  /**
   * Substitute a symbol by a value in a tree
   * @param symbol
   * @param value
   * @param in
   * @return
   */
  def substituteSymbol(symbol: Symbol, value: c.Tree => c.Tree, in: c.Tree) = new Transformer {
     override def transform(tree: c.Tree): c.Tree =
       if (tree.symbol == symbol) value(tree)
       else  super.transform(tree)
   }.transform(in)

  /**
   * Substitute a TermName by a value in a tree
   * @param name
   * @param value
   * @param in
   * @return
   */
   def substituteTermName(name: TermName, value: c.Tree => c.Tree, in: c.Tree) = new Transformer {
     override def transform(tree: c.Tree): c.Tree = tree match {
       case q"${treeName: TermName}" if treeName == name => value(tree)
       case q"$m val ${treeName: TermName}:$t = $v" if treeName == name => tree
       case _ => super.transform(tree)
     }
   }.transform(in)

  def substituteParamCall(name: TermName, value: c.Tree => c.Tree, in: c.Tree) = new Transformer {
    override def transform(tree: c.Tree): c.Tree = tree match {
      case q"$_.callParam[$t](${param: String})" if param == name.toString => value(tree)
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
     params.zip(args).foldLeft(in){(acc,c) => substituteParamCall(c._1, _ => c._2,acc)}
   }


  def callToString(tree: c.Tree): c.Tree = new Transformer {
    override def transform(tree: c.Tree): c.Tree = tree match {
      case q"call[$t](${name: TermName},..$args)" =>
        q"call[$t](${name.toString},..$args)"
      case _ => super.transform(tree)
    }
  }.transform(tree)

  def callToTermName(tree: c.Tree): c.Tree = new Transformer {
    override def transform(tree: c.Tree): c.Tree = tree match {
      case q"$x.call[$t](${name: String},..$args)" => q"$x.call[$t](${TermName(name)},..$args)"
      case _ => super.transform(tree)
    }
  }.transform(tree)


  /**
   * Get the template types of another Type.
   * getInnerTypeOf[Parser[_]](..Parser[String]..) get you Some(List(String))
   * @param typ The type in which we want to recuperate the generic part
   * @tparam T
   * @return
   */
   def getInnerTypeOf[T : TypeTag](typ: c.Type): Option[List[c.Type]] = typ match {
     case TypeRef(_, _, Nil) => None
     case TypeRef(_, _, z) if typ <:< typeOf[T] => Some(z)
     case _ => None
   }

  /**
   *
   * @param params
   * @return
   */
   def getParserParams(params: List[c.Tree]) =
     params.map{case ValDef(_,name,tpt,_) => (name,tpt.tpe)}
       .filter(_._2 <:< typeOf[Parser[_]])
       .map(x => (x._1,getInnerTypeOf[Parser[_]](x._2).get.head)) //TODO what do you mean its super ugly ?
 }
