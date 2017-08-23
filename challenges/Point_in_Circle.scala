

object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  val p = List('.', '-', ';', ',')

  for (line <- lines) {
    println(incircle(line))
  }

  def incircle(line: String):Boolean = {

    val digits = line.filter(c => c.isDigit || p.contains(c)).replaceAll(";",",")
    digits.split(",") match {
      case Array(a, b, c, d, e) => {
        val cx1 = a.toDouble
        val cy1 = b.toDouble
        val radius = c.toDouble
        val x = d.toDouble
        val y = e.toDouble

        val distance = math.sqrt(math.pow(x - cx1, 2) + math.pow(y - cy1, 2))

        distance <= radius
      }
    }
  }
}