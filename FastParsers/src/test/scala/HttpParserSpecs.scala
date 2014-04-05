import org.scalatest.FunSuite

import HttpParsers._
import scala.util.parsing.combinator._
import TestsHelper._
/**
 * Created by Eric on 05.04.14.
 */
class HttpParserSpecs extends FunSuite  {

    (1 to 101).foreach{ i =>
        test("httpparser " + "FastParsers\\src\\test\\resources\\tweet" + i){
          compareImplementations("FastParsers\\src\\test\\resources\\tweet" + i,
            httpparser.respAndMessage,
            HTTP,
            x => HTTP.parse(HTTP.respAndMessage, x))
      }
    }
}
