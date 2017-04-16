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


  /* tests */
/*  performance of "JsonParser@FastParsers" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          JSonImpl2.jsonparser.value(m)
        //println("@("+j+")JsonParser:Big@FastParsers:value")
      }
    }
  }

  performance of "JsonParser:Big@FastParsers" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl2.jsonparser.value(bigFileArray)
        //println("@("+j+")JsonParser:Big@FastParsers:value")
      }
    }
  }
*/
  performance of "JsonParser:VBig@FastParsers" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImpl2.jsonparser.value(vbigFileArray)
        //println("@("+j+")JsonParser:Big@FastParsers:value")
      }
    }
  }
/*
  performance of "JsonParser@FastParsersBoxed" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          JSonImplBoxed.jsonparser.value(m)
        //println("@("+j+")JsonParser:Big@FastParsers:value")
      }
    }
  }

  performance of "JsonParser:Big@FastParsersBoxed" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImplBoxed.jsonparser.value(bigFileArray)
        //println("@("+j+")JsonParser:Big@FastParsers:value")
      }
    }
  }
*/
  performance of "JsonParser:VBig@FastParsersBoxed" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSonImplBoxed.jsonparser.value(vbigFileArray)
        //println("@("+j+")JsonParser:Big@FastParsers:value")
      }
    }
  }

/*
  performance of "JsonParser@Combinator" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          JSON.parse(JSON.value,new FastCharSequence(m))
        //println("@("+j+")JsonParser@Combinator:value")
      }
    }
  }

  performance of "JsonParser:Big@Combinator" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          JSON.parse(JSON.value,bigFileSeq)
        //println("@("+j+")JsonParser:Big@Combinator:value")
      }
    }
  }
*/
 /* performance of "JsonParser@LMS" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          LMSJsonParser.apply(m)
        //println("@("+j+")JsonParser@Combinator:value")
      }
    }
  }

  performance of "JsonParser:Big@LMS" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          LMSJsonParser.apply(bigFileArray)
        //println("@("+j+")JsonParser:Big@Combinator:value")
      }
    }
  }

  performance of "JsonParserGen@LMS" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          LMSJsonParserGen.apply(m)
        //println("@("+j+")JsonParser@Combinator:value")
      }
    }
  }

  performance of "JsonParserGen:Big@LMS" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          LMSJsonParserGen.apply(bigFileArray)
        //println("@("+j+")JsonParser:Big@Combinator:value")
      }
    }
  }    */
/*
  performance of "JsonParserGen2@LMS" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          LMSJsonParserGen2.apply(m)
        //println("@("+j+")JsonParser@Combinator:value")
      }
    }
  }

  performance of "JsonParserGen2:Big@LMS" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          LMSJsonParserGen2.apply(bigFileArray)
        //println("@("+j+")JsonParser:Big@Combinator:value")
      }
    }
  }
*/
  performance of "JsonParserGen2:VBig@LMS" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          LMSJsonParserGen2.apply(vbigFileArray)
        //println("@("+j+")JsonParser:Big@Combinator:value")
      }
    }
  }
/*
  performance of "JsonParserGen3@LMS" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j; m <- files)
          LMSJsonParserGen3.apply(m)
        //println("@("+j+")JsonParser@Combinator:value")
      }
    }
  }

  performance of "JsonParserGen3:Big@LMS" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          LMSJsonParserGen3.apply(bigFileArray)
        //println("@("+j+")JsonParser:Big@Combinator:value")
      }
    }
  }

  performance of "JsonParserGen3:VBig@LMS" in {
    measure method "value" in {
      using(range) in { j =>
        for (i <- 1 to j)
          LMSJsonParserGen3.apply(vbigFileArray)
        //println("@("+j+")JsonParser:Big@Combinator:value")
      }
    }
  }
*/

}
