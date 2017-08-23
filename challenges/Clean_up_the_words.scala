import scala.collection.immutable.IndexedSeq
import scala.collection.immutable.TreeMap
import scala.collection.mutable
import Array._
import scala.collection.mutable.ArrayBuffer


object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {

    val result = line.map {
      case x if x.isLetter => x.toLower
      case _ => ' '
    }.replaceAll("\\s+", " ").trim

    println(result)
  }
}

