import scala.collection.immutable.IndexedSeq
import scala.collection.immutable.TreeMap
import scala.collection.mutable
import Array._

/**
 * Swap numbers challenge
 */
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(line.split(" ").map(x => x.last + x.tail.dropRight(1) + x.head).mkString(" "))
  }

}