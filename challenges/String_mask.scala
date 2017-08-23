import scala.collection.immutable.IndexedSeq
import scala.collection.immutable.TreeMap
import scala.collection.mutable
import Array._
import scala.collection.mutable.ArrayBuffer


object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {

    line.split(" ") match {
      case Array(word, mask) => {
        val result = word.zip(mask).map {
          case (a, b) => b match {
            case '1' => a.toUpper
            case _ => a.toLower
          }
        }.mkString

        println(result)
      }
    }
  }
}

