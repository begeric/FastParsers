/**
 * Created by Eric on 05.04.14.
 */
import org.scalameter.api._
import JsonParsers._
import scala.collection.mutable.ListBuffer

object JsonParserBenchmark extends PerformanceTest {

  lazy val executor = LocalExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = new LoggingReporter
  lazy val persistor = Persistor.None

  val range = Gen.enumeration("size")(10)

  val files = (1 to 5).foldLeft(new ListBuffer[String]){ (acc,i) =>
    val data = scala.io.Source.fromFile("FastParsers/src/test/resources/json" + i).getLines mkString "\n"
    acc.append(data)
    acc
  }.toList


  /* tests */
  performance of "JsonParser@FastParsers" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          jsonparser.value(m)
      }
    }
  }

  performance of "JsonParser@Combinator" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          JSON.parse(JSON.value,m)
      }
    }
  }
}
