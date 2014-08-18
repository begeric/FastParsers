/**
 * Created by Eric on 05.04.14.
 */

import fastparsers.input.InputWindow
import org.scalameter.api._
import JsonParsers._
import scala.collection.mutable.ListBuffer

import lms._
import InputWindow._

object JsonParserBenchmark extends PerformanceTest {

  lazy val executor = LocalExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = new LoggingReporter
  lazy val persistor = Persistor.None

  val range = Gen.enumeration("size")(10)

  val files = (1 to 4).foldLeft(new ListBuffer[Array[Char]]){ (acc,i) =>
    val filename = "FastParsers/src/test/resources/json" + i
    val data = scala.io.Source.fromFile(filename).getLines mkString "\n"
    acc.append(data.toCharArray)
    acc
  }.toList

  val bigFileName = "FastParsers/src/test/resources/" + "json.big1"
  val bigFile = scala.io.Source.fromFile(bigFileName).getLines mkString "\n"
  val bigFileArray = bigFile.toCharArray
  val bigFileSeq = new FastCharSequence(bigFileArray)

  val vbigFileName = "FastParsers/src/test/resources/" + "json.vbig"
  val vbigFile = scala.io.Source.fromFile(vbigFileName).getLines mkString "\n"
  val vbigFileArray = vbigFile.toCharArray
  val vbigFileSeq = new FastCharSequence(vbigFileArray)


  /*performance of "JsonParser on small inputs" in {
    measure method "FastParsers" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          JSonImplBoxed.jsonparser.value(m)
      }
    }
    measure method "LMS (gen2)" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          LMSJsonParserGen2.apply(m)
      }
    }

    /*measure method "Combinators" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          JSON.parse(JSON.value,new FastCharSequence(m))
      }
    }*/
  }*/


 /* performance of "JsonParser on a big input" in {
    measure method "FastParsers" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImplBoxed.jsonparser.value(bigFileArray)
      }
    }
 
    measure method "LMS (gen2)" in {
      using(range) in { j =>
        for (i <- 1 to j)
          LMSJsonParserGen2.apply(bigFileArray)
      }
    }

   /* measure method "Combinators" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSON.parse(JSON.value,bigFileSeq)
      }
    }*/

  }*/


  /*performance of "JsonParser on a very big input" in {
    measure method "FastParsers" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl2.jsonparser.value(vbigFileArray)
      }
    }

    measure method "Combinators" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSON.parse(JSON.value,vbigFileSeq)
      }
    }

    measure method "LMS (gen2)" in {
      using(range) in { j =>
        for (i <- 1 to j)
          LMSJsonParserGen2.apply(vbigFileArray)
      }
    }
  }*/


  performance of "Different JSonParser implementations" in {
    /*measure method "FastParsers" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl2.jsonparser.value(bigFileArray)
      }
    }*/

    /*measure method "FastParsers Boxed" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImplBoxed.jsonparser.value(vbigFileArray)
      }
    }

    measure method "FastParsers no inline" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl3.jsonparser.value(vbigFileArray)
      }
    }
    measure method "FastParsers no inline with errors reporting" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl4.jsonparser.value(vbigFileArray)
      }
    }

    measure method "FastParsers no inline with errors reporting and ignore results" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl5.jsonparser.value(vbigFileArray)
      }
    }*/

    /*measure method "FastParsers InputWindow to String" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl6.jsonparser.value(vbigFileArray)
      }
    }*/

    /*measure method "FastParsers on string input" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl1.jsonparser.value(bigFile)
      }
    }*/

    measure method "FastParsers on bigFileSeq input" in {
      using(range) in { j =>
        //for (i <- 1 to j)
          JSON.parse(JSON.value,bigFileSeq)
      }
    }

    measure method "FastParsers on bigFile input" in {
      using(range) in { j =>
        //for (i <- 1 to j)
          JSON.parse(JSON.value,bigFile)
      }
    }

  }

}
