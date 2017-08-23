import scala.collection.immutable.IndexedSeq
import scala.collection.immutable.TreeMap
import scala.collection.mutable
import Array._
import scala.collection.mutable.ArrayBuffer


object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  val right = ">>-->"
  val left = "<--<<"

  for (line <- lines) {

    def arrows(string: String): Int = {

      string.length match {
        case a if line.length < 5 => 0
        case b if line.length == 5 => {
          if (line.equals(right) || line.equals(left)) 1 else 0
        }
        case _ => {
          var result = 0

          var i = 0
          do {
            val c = string(i)
            if (c == '<') {
              if (left.equals(string.substring(i, i + 5))) {
                result += 1
                i += 3
              }
            } else if (c == '>') {
              if (right.equals(string.substring(i, i + 5))) {
                result += 1
                i += 3
              }
            }
            i += 1
          } while (i < (string.length - 4))

          result
        }
      }
    }

    println(arrows(line))
  }
}

