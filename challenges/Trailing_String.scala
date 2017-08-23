

object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(",") match {
      case Array(first, second) =>
        val Regex = (second + "$").r

        Regex.findFirstIn(first) match {
          case Some(value) => println(1)
          case None => println(0)
        }
    }
  }

}