import scala.collection.immutable.IndexedSeq
import scala.collection.immutable.TreeMap
import scala.collection.mutable
import Array._
import scala.collection.mutable.ArrayBuffer


object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split("\\|") match {
      case Array(a, b) => {
        a.split(" ").filter(s => {
          val charMap: Map[Char, Int] = s.trim.groupBy(c => c).mapValues(_.size)
          val letterMap : Map[Char, Int] = b.trim.groupBy(c => c).mapValues(_.size)

          var result = true
          for ((key, value) <- letterMap) {
            if (!charMap.contains(key) || charMap(key) < value) {
              result = false
            }
          }

          result
        }).toList match {
          case x::xs => println((x::xs).mkString(" "))
          case _ => println("False")
        }
      }
    }
  }

}

