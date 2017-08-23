object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val counter = line.foldLeft(0, 0)((counter, character) =>
       character match {
         case a if character.isUpper => (counter._1 + 1, counter._2)
         case b if character.isLower => (counter._1, counter._2 + 1)
       }
    )

    println(s"lowercase: ${percent(counter._2, line.size)} uppercase: ${percent(counter._1, line.size)}")
  }

  def percent(number: Int, amount: Int):String = "%.2f".format(number * 100.0 / amount)

}