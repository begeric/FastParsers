/**
 * Created by Eric on 05.04.14.
 */
import org.scalameter.api._
import HttpParsers._
import scala.collection.mutable.ListBuffer

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

  val files = (1 to 6).foldLeft(new ListBuffer[String]){ (acc,i) =>
    val data = (scala.io.Source.fromFile("FastParsers\\src\\test\\resources\\tweet" + i).getLines mkString "\n")
    acc.append(data)
    acc
  }.toList


  /* tests */
    performance of "HttpParser@FastParsers" in {
      measure method "respAndMessage" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            httpparser.respAndMessage(m)
        }
      }

      measure method "response" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            httpparser.response(m)
        }
      }
  }

  performance of "HttpParser@Combinators" in {
    measure method "respAndMessage" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          HTTP.parse(HTTP.respAndMessage, m)
      }
    }
    measure method "response" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          HTTP.parse(HTTP.response, m)
      }
    }
  }



}