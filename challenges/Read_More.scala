
object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  for (line <- lines) {
    line.length match {
      case x if x > 55 => {
        val trimmed = line.take(40)
        if (trimmed.contains(" ")) {
          println(trimmed.substring(0, trimmed.lastIndexOf(" ")).trim + "... <Read More>")
        } else {
          println(trimmed + "... <Read More>")
        }
      }
      case _ => println(line)
    }
  }

}

