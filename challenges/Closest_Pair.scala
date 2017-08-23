object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  val iterator = lines.iterator

  while(iterator.hasNext) {
    val n = iterator.next().toInt
    val points = readPoints(n.toInt, iterator)

    minDistance(points) match {
      case x if x > 10000 => println("INFINITY")
      case x => println(x)
    }

    //iterator.next()
  }

  def readPoints(n : Int, iterator: Iterator[String]):List[String] = {
    if (n == 0) Nil else iterator.next() :: readPoints(n - 1, iterator)
  }

  def minDistance(data: List[String]): BigDecimal = {

    val points:List[Point] = data.map( string =>
      string.split(" ") match {
        case Array(x, y) => Point(x.toInt, y.toInt)
      }
    )

    var distance : BigDecimal = BigDecimal(Double.MaxValue)

    for (i <- 0 until points.length - 1) {
      for (j <- i + 1 until points.length) {
        val d:BigDecimal = points(i).squareDistance(points(j))
        distance = d.min(distance)
      }
    }

    BigDecimal(Math.sqrt(distance.toDouble)).setScale(4, BigDecimal.RoundingMode.HALF_UP)
  }

}

case class Point(x: Int, y: Int) {

  def squareDistance(that: Point) : BigDecimal = {
    val dx = BigDecimal(x - that.x)
    val dy = BigDecimal(y - that.y)

    dx * dx + dy * dy
  }
}