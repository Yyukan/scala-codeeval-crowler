object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val sum = line.foldLeft(0)((acc, c: Char) => acc + math.pow(c.asDigit, line.length).toInt)
    println(if (line.toInt == sum) "True" else "False")
  }
}