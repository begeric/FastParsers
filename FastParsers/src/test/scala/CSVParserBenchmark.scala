/**
 * Created by Eric on 05.04.14.
 */

import fastparsers.input.InputWindow
import org.scalameter.api._
import CSVParsers._
import JsonParsers._
import scala.collection.mutable.ListBuffer

import lms._
import InputWindow._

/*object CSVParserRun {
  def main(args: Array[String]) {
    println("cho tai!!")

    val data = "[1.223, -123.243, 212143223.211]".toCharArray

    //println(cvsParser.doubles(data))
    //println(LMSCSVDoubleParserGen2.apply(data))
    //println(LMSCSVDoubleParserGen3.apply(data))

    val strlits = "[\"higher\", \"natty\", \"jah jah\"]".toCharArray
    println(cvsParser.strings(strlits))
    println(LMSCSVStringLitParseGen.apply(strlits))
    println(StringLitParseHandWritten.parseCSVStringLits(strlits, 0))

  }
}*/

object CSVParserBenchmark extends PerformanceTest {

  lazy val executor = LocalExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = new LoggingReporter
  lazy val persistor = Persistor.None

  val range = Gen.enumeration("size")(10)

  benchStringLits

  def benchDoubles = {
    val bigDoubleFileName = "FastParsers/src/test/resources/" + "csvDoubles.txt"
    val bigDoubleFile = scala.io.Source.fromFile(bigDoubleFileName).getLines mkString "\n"
    val bigDoubleFileArray = bigDoubleFile.toCharArray
    val bigDoubleFileSeq = new FastCharSequence(bigDoubleFileArray)

    performance of "CSV Double Parser" in {
      measure method "FastParsers" in {
        using(range) in { j =>
          for (i <- 1 to j)
            CSVImpl1.cvsParser.doubles(bigDoubleFileArray)
        }
      }

      /*measure method "JSON FastParsers" in {
        using(range) in { j =>
          for (i <- 1 to j)
            JSonImpl2.jsonparser.value(bigDoubleFileArray)
            //cvsParser.doubles(bigDoubleFileArray)
        }
      }*/

      /*measure method "LMS" in {
          using(range) in { j =>
            //for (i <- 1 to j)
              LMSCSVDoubleParserGen.apply(bigDoubleFileArray)
          }
      }

      measure method "LMS2" in {
          using(range) in { j =>
            //for (i <- 1 to j)
              LMSCSVDoubleParserGen2.apply(bigDoubleFileArray)
          }
      }*/

      measure method "LMS3" in {
          using(range) in { j =>
            for (i <- 1 to j)
              LMSCSVDoubleParserGen3.apply(bigDoubleFileArray)
          }
      }

      //too slow
      measure method "Combinators" in {
        using(range) in { j =>
          for (i <- 1 to j)
            CSV.parse(CSV.doubles, bigDoubleFileSeq)
        }
      }
    }
  }

  def benchBools = {
    val bigBoolFileName = "FastParsers/src/test/resources/" + "csvBooleans.txt"
    val bigBoolFile = scala.io.Source.fromFile(bigBoolFileName).getLines mkString "\n"
    val bigBoolFileArray = bigBoolFile.toCharArray
    val bigBoolFileSeq = new FastCharSequence(bigBoolFileArray)


    performance of "CSVBooleanParser:Boolean" in {
      measure method "FastParsers" in {
        using(range) in { j =>
          for (i <- 1 to j)
            CSVImpl1.cvsParser.bools(bigBoolFileArray)
        }
      }

      measure method "LMS" in {
        using(range) in { j =>
          for (i <- 1 to j)
            LMSCSVBooleanParseGen.apply(bigBoolFileArray)
        }
      }

      /*measure method "Handwritten" in {
        using(range) in { j =>
          for (i <- 1 to j)
            CSVBoolHandWritten.apply(bigBoolFileArray)
        }
      }*/

      measure method "Combinators" in {
        using(range) in { j =>
          for (i <- 1 to j)
            CSV.parse(CSV.bools, bigBoolFileSeq)
        }
      }
    }
  }

  def benchStringLits = {
    val bigStringLitFileName = "FastParsers/src/test/resources/" + "csvStringLits.txt"
    val bigStringLitFile = scala.io.Source.fromFile(bigStringLitFileName).getLines mkString "\n"
    val bigStringLitFileArray = bigStringLitFile.toCharArray
    val bigStringLitFileSeq = new FastCharSequence(bigStringLitFileArray)

    performance of "CSVStringLitParser:StringLit" in {

      //separating stringLit parsing in a different function
      /*measure method "Handwritten" in {
        using(range) in { j =>
          for (i <- 1 to j)
            StringLitParseHandWritten.parseCSVStringLits(bigStringLitFileArray, 0)
        }
      }

      //inlining the parsing of string literal
      // attention: inlining of functions isNEOI, currentInput, advance makes no difference
      measure method "Handwritten2" in {
        using(range) in { j =>
          for (i <- 1 to j)
            StringLitParseHandWritten.parseCSVStringLits2(bigStringLitFileArray, 0)
        }
      }

      //using CharArrayStructs: benchmarks now get interesting
      measure method "Handwritten3" in {
        using(range) in { j =>
          for (i <- 1 to j)
            StringLitParseHandWritten.parseCSVStringLits3(bigStringLitFileArray, 0)
        }
      }

      //an extra boxing layer around the result of a parse
      measure method "Handwritten4" in {
        using(range) in { j =>
          for (i <- 1 to j)
            StringLitParseHandWritten.parseCSVStringLits4(bigStringLitFileArray, 0)
        }
      }

      //code gen a la LMS for rep1 and stringlit, with extra layer of boxing for results
      // a | b = lift(lift(a) or b)
      // repsep(p,q) = p ~ rep(q ~ p) | success(Nil)
      // stringlit = '\\ ~ anything | not('\"')
      measure method "Handwritten4" in {
        using(range) in { j =>
          for (i <- 1 to j)
            StringLitParseHandWritten.parseCSVStringLits4(bigStringLitFileArray, 0)
        }
      }

      measure method "Handwritten5" in {
        using(range) in { j =>
          for (i <- 1 to j)
            StringLitParseHandWritten.parseCSVStringLits4(bigStringLitFileArray, 0)
        }
      }*/

      measure method "FastParsers" in {
        using(range) in { j =>
          for (i <- 1 to j)
            CSVImpl1.cvsParser.strings(bigStringLitFileArray)
        }
      }

      measure method "LMS" in {
        using(range) in { j =>
          for (i <- 1 to j)
            LMSCSVStringLitParseGen.apply(bigStringLitFileArray)
        }
      }


      measure method "Combinators" in {
        using(range) in { j =>
          for (i <- 1 to j)
            CSV.parse(CSV.strings, bigStringLitFileSeq)
        }
      }
    }
  }
}
