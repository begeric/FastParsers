
import scala.collection.mutable.HashMap
import scala.util.parsing.combinator._
import scala.util.parsing.input._
import fastparsers.input.InputWindow

/**
 * Created by Eric on 05.04.14.
 */
object HttpParsers {
  case class Response(status: Int = 200,contentLength: Int = 0,connection: String = "", chunked: Boolean = false,upgrade: Boolean = false)

  case class Request(requestType: String,url: Url,contentLength: Int,connection: String,chunked: Boolean = false,upgrade: Boolean = false)

  case class Url(schema: String,hostName: String,path: String,queryString: String, fragment: String, port: Int = 80)



  object HTTP extends JavaTokenParsers {

    val requestTypes = List(
      "connect",
      "copy",
      "checkout",
      "delete",
      "get",
      "head",
      "lock",
      "merge",
      "mkactivity",
      "mkcol",
      "move",
      "msearch",
      "notify",
      "options",
      "post",
      "propfind",
      "proppatch",
      "put",
      "report",
      "subscribe",
      "trace",
      "unlock",
      "unsubscribe"
    )

    //removing cr-lf from whiteSpace
    override val whiteSpace = """[ \t\f\x0B\f]""".r

    //the \r is optional
    val crlf = """\r?\n""".r

    def wild = body(201927)
    val wildRegex = """[^\r\n]*""".r
    val headerName = """[a-zA-Z][\w-]*""".r
    val hexNum = """[0-9A-F]+""".r

    // 0x23 == '#', 0x74 == 'del'
    val urlChar = """[^\x00-\x20#\?\x7F]""".r

    def hexToInt(s: String) = Integer.parseInt(s, 16)

    //def message: fastparsers.parsers.Parser[Any] = /*request | */ response

    //using the amazing bind operator!
    def respAndMessage: Parser[(Response, String)] = response >> {
      case rsp =>
        {
          if (rsp.chunked) chunkedParser
          else if (rsp.contentLength == 0) "" <~ crlf
          else body(rsp.contentLength)
          //TODO: other cases to be dealt with
        } ^^ { (rsp, _) }
    }

    /**
     * to match line terminators with ".", suggestion by
     * http://stackoverflow.com/questions/3222649/any-character-including-newline-java-regex
     *
     * TODO: if the rest of the fastparsers.input is smaller than i, deal with it.
     */
    def body(i: Int): Parser[String] = ("(?s:.{" + i + "})").r

    // this one would need tail recursion
    def chunkedParser: Parser[String] = chunkSize >> {
      case 0 => "" <~ crlf
      //TODO: whitespaces are skipped before parsing body(i), needs to be changed
      case i => (body(i) ~ chunkedParser) ^^ { case x ~ y => x + y }
    }

    def chunkSize: Parser[Int] = hexNum <~ (wildRegex ~ crlf) ^^ hexToInt

    //TODO: the asInstanceOf here in a bit ugly, the types are already known
    // when the result has been parsed
    def response: Parser[Response] = status ~ headers <~ crlf ^^ {
      case st ~ hds => Response(
        status = st,
        contentLength = hds("content-length").asInstanceOf[Int],
        connection = hds.getOrElse("connection", "").asInstanceOf[String],
        chunked = hds.getOrElse("chunked", false).asInstanceOf[Boolean],
        upgrade = hds.getOrElse("upgrade", false).asInstanceOf[Boolean]
      )
    }

    def status: Parser[Int] =
      ("HTTP/" ~ decimalNumber) ~> wholeNumber <~ (wildRegex ~ crlf) ^^ (_.toInt)

    /*
     * Headers to deal with specially:
     *  - connection: keep-alive, close
     *  - content-length
     *  - transfer-encoding: chunked
     *  - upgrade
     *  - proxy-connection
     */

    //have a typeclass for response, which provides a method for empty (keep for later)
    //have the user provide and object with all fields interested in and default values,
    //we fill it up progressively.

    def headers: Parser[Map[String, Any]] = rep(header) ^^ {
      case xs => xs.foldLeft(Map[String, Any]()) {
        case (map, None) => map
        case (map, Some((hName, hValue))) => map + (hName -> hValue)
      }
    }

    def header: Parser[Option[(String, Any)]] = (headerName <~ ":") ~ (wildRegex <~ crlf) ^^ {
      case hName ~ prop => collect(hName.toLowerCase, prop)
    }

    /*TODO: the handwritten code has an order for these which is not alphabetic
     * coincidence? */

    //TODO: make whiteSpace consuming explicit
    def requestType: Parser[String] = ("(?i)" + HTTP.requestTypes.mkString("|")).r ^^ { x => x.toLowerCase() }
    def schema: Parser[String] = "[a-z]+".r
    def hostName: Parser[String] = "[a-z0-9-.]+".r
    def host: Parser[Map[String, String]] =
      hostName ~ opt(":" ~> wholeNumber) ^^ {
        case x ~ Some(y) => Map("hostName" -> x, "port" -> y)
        case x ~ None => Map("hostName" -> x)
      }

    def reqFragment: Parser[Map[String, String]] =
    //the greediness of the first regex prevents ambiguity
      ("#*".r ~> (urlChar | "?" | "#")*) ^^ { case xs => Map("fragment" -> xs.mkString("")) }

    //TODO: Don't care about the actual type of this now
    def httpInfo: Parser[Any] = opt("HTTP/" ~ decimalNumber)

    def reqPath: Parser[Map[String, String]] =
      (urlChar*) ~ opt(("?" ~> queryString) |
        ("#" ~> reqFragment)
      ) <~ httpInfo ^^ {
        case urlc ~ Some(aMap: Map[String, String]) =>
          aMap + ("path" -> ("/" + urlc.mkString + {
            if (aMap.isDefinedAt("queryString")) "?" + aMap("queryString")
            else if (aMap.isDefinedAt("fragment")) "#" + aMap("fragment")
            else ""
          }))

        case urlc ~ None => Map("path" -> ("/" + urlc.mkString))
      }

    def queryString: Parser[Map[String, String]] =
      ("\\?*".r ~> (urlChar | "?")*) ~ opt("#" ~> reqFragment) ^^ {
        case x ~ Some(aMap) =>
          aMap + ("queryString" -> (x.mkString + "#" + aMap("fragment")))
        case x ~ None => Map("queryString" -> x.mkString)
      }

    def url: Parser[Url] =
      ((schema <~ ("://" | "." | "[0-9]")) //TODO: not too sure about this one.
        ~ opt(host)
        ~ ("/" ~> reqPath | "?" ~> queryString | "/|*".r ~> reqPath)
        ) ^^
        {
          case sc ~ Some(h) ~ aMap =>
            Url(
              schema = sc,
              hostName = h.getOrElse("hostName", ""),
              path = aMap.getOrElse("path", ""),
              queryString = aMap.getOrElse("queryString", ""),
              fragment = aMap.getOrElse("fragment", ""),
              port = h.getOrElse("port", "80").toInt
            )
        }

    def requestStatus: Parser[Any] = requestType ~ url <~ crlf
    def request: Parser[Any] = requestStatus ~ headers <~ crlf



    def collect(hName: String, prop: String): Option[(String, Any)] = hName match {
      case "connection" | "proxy-connection" if prop == "keep-alive" || prop == "close" => Some((hName, prop))
      case "content-length" => Some((hName, prop.toInt)) //TODO: deal with numformatexception
      case "transfer-encoding" if prop == "chunked" => Some(("chunked", true))
      case "upgrade" => Some((hName, true))
      case _ => None
    }
  }

  def processResp(x:(Int,HashMap[String, String])) = Response(
    status = x._1,
    contentLength = x._2("content-length").toInt,
    connection = x._2.getOrElse("connection", ""),
    chunked = x._2.getOrElse("chunked", false).asInstanceOf[Boolean],
    upgrade = x._2.getOrElse("upgrade", false).asInstanceOf[Boolean]
  )

  object HTTPImpl1 {
    import fastparsers.framework.implementations.FastParsers
    import FastParsers._
    val httpparser = FastParser  {
      def restOfLine = takeWhile(_ != '\n')
      def headerName = takeWhile(x => (x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z') || x == '-')
      def header = (headerName <~ ':' ~ whitespaces) ~ restOfLine
      def headers = (header <~ '\n').foldLeft[HashMap[String, String]](new HashMap[String, String](),(acc,c) => acc += (c._1 -> c._2))
      def status = lit("HTTP/") ~ decimalNumber ~ whitespaces ~> (number ^^ (_.toString.toInt)) <~ restOfLine
      def response = (status <~ '\n') ~ headers <~ '\n' ^^ processResp
      def respAndMessage = response >> (r => (take(r.contentLength) ^^ (y => (r,y))))
    }
  }

  object HTTPImpl2 {
    val HTTP = "HTTP/".toCharArray
    import fastparsers.framework.implementations.FastParsersCharArray._
    val httpparser = FastParsersCharArray  {
      def restOfLine = takeWhile(_ != '\n')
      def headerName = takeWhile(x => (x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z') || x == '-')
      def header = (headerName <~ ':' ~ whitespaces) ~ restOfLine ^^ {case (a,b) => (a.mkString, b.mkString)}
      def headers = (header <~ '\n').foldLeft[HashMap[String, String]](new HashMap[String, String](),(acc,c) => acc += (c._1 -> c._2))
      def status = lit(HTTP) ~ decimalNumber ~ whitespaces ~> (number ^^ (_.toString.toInt)) <~ restOfLine
      def response = (status <~ '\n') ~ headers <~ '\n' ^^ processResp
      def respAndMessage = response >> (r => (take(r.contentLength) ^^ (y => (r,y))))
    }
  }


  object HTTPImpl3 {
    val HTTP = "HTTP/".toCharArray
    import fastparsers.framework.implementations.FastParsersCharArray._
    import fastparsers.input.InputWindow.InputWindow

    def collect(r: Response, hName: InputWindow[_], prop: InputWindow[_]): Response = {
      if((hName == "connection" || hName == "proxy-connection") && (prop == "keep-alive" || prop == "close")){
        r.copy(connection = prop.toString)
      }else if(hName == "content-length"){
        r.copy(contentLength = prop.toString.toInt)
      }else if(hName == "transfer-encoding" && prop == "chunked"){
        r.copy(chunked = true)
      }else if(hName == "upgrade"){
        r.copy(upgrade = true)
      }else {
        r
      }
    }

    val httpparser = FastParsersCharArray  {
      def restOfLine = takeWhile2(_ != '\n')
      def headerName = takeWhile2(x => (x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z') || x == '-')
      def header = (headerName <~ ':' ~ whitespaces) ~ restOfLine
      def headers = (header <~ '\n').foldLeft(Response(),{(acc: Response, c: Tuple2[InputWindow[_],InputWindow[_]]) => collect(acc, c._1, c._2)})
      def status = lit(HTTP) ~ decimalNumber ~ whitespaces ~> (number ^^ (_.toString.toInt)) <~ restOfLine
      def response = (status <~ '\n') ~ headers <~ '\n'
    }
  }
}
