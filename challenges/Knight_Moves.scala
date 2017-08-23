object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(Seq(line).flatMap(position => {
      val row: Char = position.head
      val column: Char = position.last

      List(
        "" + (row + 2).toChar + (column - 1).toChar, "" + (row + 2).toChar + (column + 1).toChar,
        "" + (row - 2).toChar + (column - 1).toChar, "" + (row - 2).toChar + (column + 1).toChar,
        "" + (row + 1).toChar + (column - 2).toChar, "" + (row - 1).toChar + (column - 2).toChar,
        "" + (row + 1).toChar + (column + 2).toChar, "" + (row - 1).toChar + (column + 2).toChar
      ).filter({
        case x if x.last < '1' || x.last > '8' => false
        case x if x.head < 'a' || x.head > 'h' => false
        case _ => true
      }).sorted
    }).mkString(" "))
  }

}
