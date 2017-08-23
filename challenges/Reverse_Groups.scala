object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(reverseK(line))
  }

  def reverseK(line: String) = {
    line.split(";") match {
      case Array(text, number) =>
        val list = text.split(",").map(_.toInt).toList
        list.grouped(number.toInt).map(x =>
          if (x.length == number.toInt) x.reverse else x).flatten.mkString(",")
    }
  }
}