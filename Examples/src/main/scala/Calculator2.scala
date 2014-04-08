import scala.collection.immutable.HashMap

/**
 * Created by Eric on 07.04.14.
 */
object Calculator2 {

  def main(args: Array[String])  {
    import FastParsers._

    var values = new HashMap[String,Int]()

    var numRes = 0
    def getNextVar = {numRes += 1;"res" + numRes}

    def getVar(v:String) = values.get(v) match {
      case None => 0
      case Some(x) => x
    }

    def setVar(name:String,value:Int) = {
      values += name -> value
      (name,value)
    }

    def setRes(value:Int) = setVar(getNextVar,value)

    def exec(x:(Int,Option[(Char,Int)])) = x match {
      case (y,None) => y
      case (y1,Some((op,y2))) => op match {
        case '+' => y1 + y2
        case '-' => y1 - y2
        case '*' => y1 * y2
        case '/' => y1 / y2
      }
    }

    def func(name:String,args:List[Int]):Int = (name,args) match {
      case ("abs",x::Nil)     => Math.abs(x)
      case ("min",x::y::Nil)  => Math.min(x,y)
      case ("max",x::y::Nil)  => Math.max(x,y)
      case ("rnd",Nil)        => (Math.random() * 100).toInt
      case ("rnd",x::y::Nil)  => (Math.random() * (y - x)).toInt + x
      case ("pow",x::y::Nil)  => Math.pow(x,y).toInt
      case ("sqrt",x::Nil)    => Math.sqrt(x).toInt
      case _ =>
        println("incorrect function " + name + " with args " + args.mkString)
        0
    }

    def alpha(x:Char) = x >= 'a' && x <= 'z' || x >= 'A' && x <= 'Z'
    def alphanum(x:Char) = alpha(x) || x >= '0' && x <= '9'

    val parser = FastParser{
        def op1 = lit("+") ^^^ '+' | lit("-") ^^^ '-'
        def op2 = lit("*") ^^^ '*' | lit("/") ^^^ '/'
        def funccall = ident ~ (lit("(") ~> repsep(expr,",") <~ ")") ^^ (x => func(x._1,x._2))
        def factor:Parser[Int] = number | lit("(") ~> expr <~ ")" | funccall | ident ^^ getVar
        def term:Parser[Int] = factor ~ opt(op2 ~ term) ^^ exec
        def expr:Parser[Int] = term ~ opt(op1 ~ expr) ^^ exec
        def assign = ident ~ (lit("=") ~ whitespaces ~> expr)
        def start = phrase(assign ^^ (x => setVar(x._1,x._2)) | expr ^^ setRes) //the order is important !! (because of opt(op1 ~ expr))
    }

    println("Please enter an expression (or enter an empty line to quit)")

    var cont = true
    while (cont) {
      scala.io.StdIn.readLine() match {
        case "" => cont = false
        case line =>
          parser.start(line) match {
            case Success((res,value)) =>
              println(res + " = " + value)
            case Failure(msg) => println("failure : " + msg)
          }
      }
    }
  }
}