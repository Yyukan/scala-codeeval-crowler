
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(",") match {
      case Array(a, b, c, d, e, f, g, h) =>
      val r1x1 = a.toInt
      val r1y1 = b.toInt
      val r1x2 = c.toInt
      val r1y2 = d.toInt

      val r2x1 = e.toInt
      val r2y1 = f.toInt
      val r2x2 = g.toInt
      val r2y2 = h.toInt

      val isIntersect = (r1x1 <= r2x2) && (r1x2 >= r2x1) && (r1y1 >= r2y2) && (r1y2 <= r2y1)

      if (isIntersect) println("True") else println("False")
    }


  }

}
