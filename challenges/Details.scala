object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(line.split(",").toList.map {
      case x => (x.indexOf('Y') - x.lastIndexOf('X') - 1)
    }.min)
  }

}

