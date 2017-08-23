/*Sample code to read in test cases:*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(remove(line))
  }

  def remove(line: String):String = {
    line.split(",") match {
      case Array(text, symbols) => text.trim.filterNot((c: Char) => symbols.trim.contains(c))
    }
  }
}
