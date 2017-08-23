object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  for (line <- lines) {
    line.split("\\|") match {
      case Array(a, b , c) =>
        val repeatWordLength = a.trim.toInt
        val lastLetter:Char = b.trim.head
        val numbers = c.trim.split(" ").map(_.toInt).toList

        val space = numbers.min
        val constant = space - ' '.toInt

        println(numbers.map(x => (x - constant).toChar).mkString)

    }
  }

}