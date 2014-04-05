/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 12.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

import scala.collection.mutable.HashMap

case class Response(status: Int,contentLength: Int,connection: String, chunked: Boolean = false,upgrade: Boolean = false)

object Test {

  def processResp(x:(Int,HashMap[String, String])) = Response(
      status = x._1,
      contentLength = x._2("content-length").toInt,
      connection = x._2.getOrElse("connection", ""),
      chunked = x._2.getOrElse("chunked", false).asInstanceOf[Boolean],
      upgrade = x._2.getOrElse("upgrade", false).asInstanceOf[Boolean]
    )

 def main(args: Array[String]) {
   import FastParsers._

   val parser = FastParser{
     def data = takeWhile(_ => true)
     def restOfLine = takeWhile(_ != '\n')
     def headerName = takeWhile(x => (x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z') || x == '-')
     def header = (headerName <~ ':' ~ whitespaces) ~ restOfLine
     def headers = (header <~ '\n').foldLeft[HashMap[String, String]](new HashMap[String, String](),(acc,c) => acc += (c._1 -> c._2))
     def status = lit("HTTP/") ~ decimalNumber ~ whitespaces ~> number <~ restOfLine
     def response = (status <~ '\n') ~ headers <~ '\n' ^^ processResp
     def respAndMessage = response ~ data
   }
   //" -.3    \"hell\" -458"

   val file = scala.io.Source.fromFile("FastParsers/src/test/resources/tweet75").getLines mkString "\n"
   parser.respAndMessage(file) match {
     case Success(x) => println(x)
     case Failure(msg) => println("failure: " + msg)
   }
 }
}