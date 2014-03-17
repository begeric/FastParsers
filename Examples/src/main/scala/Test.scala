/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
import FastParsers._
import scala.collection.mutable
import StreamMarked._

import scala.util.parsing.input._
object Test {
 implicit def stringToCharSeqReader(s:String) = new CharSequenceReader(s)
 implicit def stringToStreamMarkedArray(s:String) = new StreamMarkedArray(s.toCharArray)
 //implicit def stringToArray(s:String) = s.toCharArray


 def main(args: Array[String]) {

   case class Response(
    status: Int,
    contentLength: Int,
    connection: String,
    chunked: Boolean = false,
    upgrade: Boolean = false // keep alive is kept in the connection field
   )

   def collect2(v:(String,String)) = collect(v._1.toLowerCase,v._2)

   def collect(hName: String, prop: String): Option[(String, Any)] = hName match {
     case "connection" | "proxy-connection" if (prop == "keep-alive" || prop == "close") => Some((hName, prop))
     case "content-length" => Some((hName, prop.toInt)) //TODO: deal with numformatexception
     case "transfer-encoding" if (prop == "chunked") => Some(("chunked", true))
     case "upgrade" => Some((hName, true))
     case _ => None
   }

   def headersMap(map: Map[String, Any],v:Any) = collect2(v.asInstanceOf[(String,String)])  match {
     case Some((name,value)) => map + (name -> value)
     case None => map
   }

   def processResp(x:Any) = x match {
     case (st:Int,hds:Map[String, Any]) => Response(
       status = st,
       contentLength = hds("content-length").asInstanceOf[Int],
       connection = hds.getOrElse("connection", "").asInstanceOf[String],
       chunked = hds.getOrElse("chunked", false).asInstanceOf[Boolean],
       upgrade = hds.getOrElse("upgrade", false).asInstanceOf[Boolean]
     )
   }

   /*val httpResponParser = FastParser{
     def int = rep1(range('0','9'))
     def intparse = int ^^ {case x:List[Char] => x.mkString.toInt}
     def decimal = int ~ '.' ~ int
     def clrf = seq("\r\n") | '\n'
     def wss = rep(' ' || '\t' || '\f')//rep(alt(Array(' ','\t','\f'))) // x0B ??
     def wild = (not(clrf) ~> wildcard[Char]).repFold[StringBuilder](new StringBuilder(""),(acc,c) => acc.append(c)) ^^ {case x:StringBuilder => x.result}
     def status = seq("HTTP/") ~ decimal ~ wss ~> intparse <~ wss ~ wild
     def data = (wildcard[Char]).repFold[StringBuilder](new StringBuilder(""),(acc,c) => acc.append(c))
     def headerName = (range('a','z') || range('A','Z') || '-').repFold[StringBuilder](new StringBuilder(""),(acc,c:Any) => acc.append(c)) ^^ {case x:StringBuilder => x.result}
     def header = headerName ~ -(':' ~ wss) ~ wild
     def headers = (header <~ clrf).repFold[Map[String, Any]](Map[String, Any](),headersMap)

     //def response = status ~ -clrf ~ headers <~ clrf ^^  processResp
     def response = status ~ -(clrf) ~ headers <~ clrf ^^  processResp

     def respAndMessage = response ~ data
   }  */

   def buildString(acc:StringBuilder,c:Char) = acc.append(c)

   val httpResponParser = FastParser{
     def int = rep1(range('0','9'))
     def intparse = int
     def decimal = int ~ '.' ~ int
     def clrf = seq("\r\n") | '\n'
     def wss = rep(' ' || '\t' || '\f')
     def wild = rep(not(clrf) ~> wildcard[Char])
     def status = seq("HTTP/") ~ decimal ~ wss ~> intparse <~ wss ~ wild
     //def data = wildcard[Char].repFold[StringBuilder](new StringBuilder(),buildString)
    // def data = wildcard[Char].repFold[java.lang.StringBuilder](new java.lang.StringBuilder(),(acc,c) => acc.append(c)) ^^ {case x:java.lang.StringBuilder => x.toString}
     //def data = wildcard[Char].repFold[StringBuilder](new StringBuilder(),(acc,c) => acc.append(c))  ^^ {case x:StringBuilder => x.toString}
     def data = takeWhile[Char](_ => true) ^^ {case x:Array[Char] => x.toString}
     def headerName = rep(range('a','z') || range('A','Z') || '-')
     def header = headerName ~ -(':' ~ wss) ~ wild
     def headers = rep(header <~ clrf)

     //def data = takeWhile(_ => true)
     //def response = status ~ -clrf ~ headers <~ clrf ^^  processResp
     def response = status ~ -(clrf) ~ headers <~ clrf

     def respAndMessage = response ~ data

   }

   val lines = (scala.io.Source.fromFile("FastParsers\\src\\test\\resources\\tweet1").getLines mkString "\n")

   val tmp = new StreamMarkedArray("dsfhkgodkghf".toCharArray)


   /*var now = System.nanoTime
   var inputpos = 0
   val inputsize = lines.size
   var builder = new StringBuilder()
   while (inputpos < inputsize){
     //builder = builder.append(lines(inputpos))
     //takeWhile(lines(inputpos))
     takeWhile(lines(inputpos))
     inputpos = inputpos + 1
   }
   val r = lines.substring(0,inputsize - 1).toCharArray.toString
   //println(r)
   var micros = (System.nanoTime - now) /1e6
   println("%f ms".format(micros))   */

   val now2 = System.nanoTime
   httpResponParser.data(lines) match {
     case Success(result) =>
       val micros2 = (System.nanoTime - now2) /1e6
       //println(result)
       println("%f ms ".format(micros2))
     case Failure(msg) => println("error : " + msg)
   }
 }

}
/*
var now = System.nanoTime
var inputpos = 0
val inputsize = lines.size
var builder = new StringBuilder()
while (inputpos < inputsize){
builder = builder.append(lines(inputpos))
inputpos = inputpos + 1
}
//println(r)
var micros = (System.nanoTime - now) /1e6
println("%f ms".format(micros))
                                       */