/**
 * Created by Eric on 05.04.14.
 */
import org.scalameter.api._
import HttpParsers._
import scala.collection.mutable.ListBuffer
import lms._

object HttpParserBenchmark extends PerformanceTest {

  /* configuration */

  lazy val executor = LocalExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = new LoggingReporter
  lazy val persistor = Persistor.None

  /* inputs */

  val range = Gen.enumeration("size")(10)


  val files = (1 to 1).foldLeft(new ListBuffer[(Array[Char], String)]){ (acc,i) =>
    val fileName = "FastParsers/src/test/resources/tweet" + i
    val data = scala.io.Source.fromFile(fileName).getLines mkString "\n"
    acc.append((data.toCharArray,data))
    acc
  }.toList


  /* tests */
    performance of "HttpParser response and message" in {
      /*measure method "FastParsers" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            httpparser.respAndMessage(m._1)
        }
      }

      measure method "Combinators" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            HTTP.parse(HTTP.respAndMessage, m._2)
        }
      }*/

  }

  performance of "HttpParser response only" in {
      /*measure method "FastParsers" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            httpparser.response(m._1)
        }
      }

      measure method "Combinators" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
          HTTP.parse(HTTP.response, m._2)
        }
      }

      measure method "LMS" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            HttpResponseParser.apply(m._1)
          //println("@("+j+")HttpParser@Combinators:respAndMessage")
        }
      }
      */

  }

	/*performance of "HttpParser@LMS" in {
    measure method "response" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          HttpResponseParser.apply(m._1)
        //println("@("+j+")HttpParser@Combinators:respAndMessage")
      }
    }
    measure method "response" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            httpparser.response(m._2)
          //println("@("+j+")HttpParser@FastParsers:response")
        }
      }
  }*/


}