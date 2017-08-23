/*Sample code to read in test cases:*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (line <- lines) {
    val points = line.replaceAll(" ", "").
      replace(")(",",").replace("(", "").replace(")","").split(",").map(_.toInt).toList

    println(distance(points(0), points(1), points(2), points(3)))
  }

  def distance(x1: Int, y1:Int, x2:Int, y2:Int):Int =
    math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)).toInt
}