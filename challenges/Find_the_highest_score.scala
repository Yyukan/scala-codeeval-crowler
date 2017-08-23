import scala.collection.immutable.IndexedSeq
import scala.collection.immutable.TreeMap
import scala.collection.mutable
import Array._
import scala.collection.mutable.ArrayBuffer


object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    // parse line to the List of integers, like List(1,2,3) List (4,5,6) etc
    val rows : Array[Array[Int]] = line.split("\\|").map(x => x.trim.split(" ").map(x => x.trim.toInt))

    // produce vector which contains sequence by columns
    val columns = for {
      i <- rows(0).indices
      j <- rows.indices
    } yield rows(j)(i)

    // calculate max for every column group
    println(columns.grouped(rows.length).map(_.max).mkString(" "))

  }
}

