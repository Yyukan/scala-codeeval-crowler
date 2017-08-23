import scala.collection.Map

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val numbers = line.split(",")
    val occurences = numbers.groupBy(_.toInt).map(p => (p._1, p._2.length))
    occurences.filter(p => p._2 > numbers.size / 2) match {
      case m:Map[Int, Int] if m.size == 0  => println("None")
      case m:Map[Int, Int] => println(m.keys.mkString)
    }

  }

}
