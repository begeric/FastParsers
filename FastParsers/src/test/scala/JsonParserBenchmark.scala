/**
 * Created by Eric on 05.04.14.
 */
import org.scalameter.api._
import JsonParsers._
import scala.collection.mutable.ListBuffer

class JsonParserBenchmark extends PerformanceTest {

  lazy val executor = LocalExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = new LoggingReporter
  lazy val persistor = Persistor.None

  val range = Gen.enumeration("size")(10)

  val files = (1 to 5).foldLeft(new ListBuffer[(String,String)]){ (acc,i) =>
    val filename = "FastParsers/src/test/resources/json" + i
    val data = scala.io.Source.fromFile(filename).getLines mkString "\n"
    acc.append((data,filename))
    acc
  }.toList

  val bigFileName = "FastParsers/src/test/resources/" + "json.big1"
  val bigFile = scala.io.Source.fromFile(bigFileName).getLines mkString "\n"


  /* tests */
  performance of "JsonParser@FastParsers" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files){
          jsonparser.value(m._1)
          println("@(" + i + ", " + j + ")Parsed " + m._2)
        }
      }
    }
  }

  performance of "JsonParser:Big@FastParsers" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j) {
          jsonparser.value(bigFile)
          println("@(" + i + ", " + j + ")Parsed " + bigFileName)
        }
      }
    }
  }

  performance of "JsonParser@Combinator" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files) {
          JSON.parse(JSON.value,m._1)
          println("@(" + i + ", " + j + ")Parsed " + m._2)
        }
      }
    }
  }

  performance of "JsonParser:Big@Combinator" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j){
          JSON.parse(JSON.value,bigFile)
          println("@(" + i + ", " + j + ")Parsed " + bigFileName)
        }
      }
    }
  }
}
