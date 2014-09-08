
import JsonParsers._
import org.scalameter.api._
import org.scalameter.PerformanceTest
import org.scalameter.reporting.LoggingReporter

/**
 * Created by Eric on 12.05.14.
 */
object ParsersMixinBenchmark extends PerformanceTest {

  lazy val executor = LocalExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = new LoggingReporter
  lazy val persistor = Persistor.None

  val range = Gen.enumeration("size")(10)


  val bigFileName = "FastParsers/src/test/resources/json.big1"
  val bigFile = scala.io.Source.fromFile(bigFileName).getLines mkString "\n"
  val bigFileArray = bigFile.toCharArray

  /*performance of "JsonParser:@FastParsers" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl1.jsonparser.value(bigFile)
      }
    }
  }

  performance of "JsonParser:@FastParsersCharArray" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl2.jsonparser.value(bigFileArray)
      }
    }
  }

  performance of "JsonParser:@FastParsersCharArrayNoInline" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl3.jsonparser.value(bigFileArray)
      }
    }
  }

  performance of "JsonParser:@FastParsersCharArrayDefaultErrors" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl4.jsonparser.value(bigFileArray)
      }
    }
  }

  performance of "JsonParser:@FastParsersCharArrayIgnoreResults" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl5.jsonparser.value(bigFileArray)
      }
    }
  }*/

}
