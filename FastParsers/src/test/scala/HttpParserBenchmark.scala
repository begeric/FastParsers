/**
 * Created by Eric on 05.04.14.
 */
import org.scalameter.api._
import HttpParsers._
import scala.collection.mutable.ListBuffer
import lms.parsing.gen._

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


  val files = (1 to 10).foldLeft(new ListBuffer[(String, CharSequence)]){ (acc,i) =>
    val fileName = "FastParsers/src/test/resources/tweet" + i
    val data = scala.io.Source.fromFile(fileName).getLines mkString "\n"
    val charSeq = new FastCharSequence(data.toCharArray)
    acc.append((data,charSeq))
    acc
  }.toList


  /* tests */
    performance of "HttpParser@FastParsers" in {
      measure method "respAndMessage" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            httpparser.respAndMessage(m._1)
          //println("@("+j+")HttpParser@FastParsers:respAndMessage")
        }
      }

      measure method "response" in {
        using(range) in { j =>
          for (i <- 1 to j; m <- files)
            httpparser.response(m._1)
          //println("@("+j+")HttpParser@FastParsers:response")
        }
      }
  }

  performance of "HttpParser@Combinators" in {
    measure method "respAndMessage" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          HTTP.parse(HTTP.respAndMessage, m._2)
        //println("@("+j+")HttpParser@Combinators:respAndMessage")
      }
    }
    measure method "response" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          HTTP.parse(HTTP.response, m._2)
        //println("@("+j+")HttpParser@Combinators:response")
      }
    }
  }
  
	performance of "HttpParser@LMS" in {
    measure method "response" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          HttpResponseParser.apply(m._1.toCharArray)
        //println("@("+j+")HttpParser@Combinators:respAndMessage")
      }
    }
  }


}