object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  def toCoordinate(string: String): List[Int] = {
    string.tail.dropRight(1).split(",").map(_.toInt).toList
  }

  def flyover(xs: List[Int], ys: List[Int]): Int = {
    val angle: Float = ys.last.toFloat / xs.last.toFloat

    var result = 0

    for {
      y <- ys zip ys.tail
      x <- xs zip xs.tail
    } yield {
      if ((angle * x._1) < y._2 && (angle * x._2) > y._1) {
        result += 1
      }
    }

    result
  }

  for (line <- lines) {
    line.split(" ") match {
      case Array(a, b) => {
        println(flyover(toCoordinate(a), toCoordinate(b)))
      }
    }
  }
}
