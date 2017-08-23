object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(";") match {
      case Array(a, b) => {
        b.split(" ").map(_.toInt).sliding(a.toInt).map(_.sum).max match {
          case x if x < 0 => println(0)
          case x => println(x)
        }
      }
    }
  }

}
