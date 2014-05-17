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

  val files = (1 to 100).foldLeft(new ListBuffer[(Array[Char], String, FastCharSequence)]){ (acc,i) =>
    val fileName = "FastParsers/src/test/resources/tweet" + i + "ho"
    val data = scala.io.Source.fromFile(fileName).getLines mkString "\n"
    acc += ((data.toCharArray,data, new FastCharSequence(data.toCharArray)))
    acc
  }.toList

  /* tests */
    /*performance of "HttpParser response and message" in {
      measure method "FastParsers" in {
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
      }

  }*/

  performance of "HttpParser response only" in {
      measure method "FastParsers" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            HTTPImpl3.httpparser.response(m._1)
        }
      }

      measure method "Combinators" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
          HTTP.parse(HTTP.response, m._3)
        }
      }

      measure method "LMS" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            HttpResponseParser.apply(m._1)
        }
      }
      

  }

	/*performance of "HttpParser@LMS" in {
    measure method "response" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          HttpResponseParser.apply(m._1)
      }
    }
    measure method "response" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            httpparser.response(m._2)
        }
      }
  }*/


}