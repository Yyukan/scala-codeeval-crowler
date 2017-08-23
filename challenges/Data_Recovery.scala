import scala.collection.immutable.TreeMap
import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(";") match {
      case Array(a, b) => {
        val words: List[String] = a.split(" ").toList
        var order = b.split(" ").map(_.toInt).toList

        // added missed index to the end of indexes
        order = order ::: (1 to words.length).diff(order).toList

        // zip every index with words, sort by index and print
        println(order.zip(words).sortBy(_._1).map(_._2).mkString(" "))
      }
    }
  }
}


