
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  val alphabet:Set[Char] = ('a' to 'z').toSet

  for (line <- lines) {
    println(isPangram(line))
  }

  def isPangram(line: String): String = {
    val letters = line.toLowerCase.replace(" ", "").toSet

    alphabet.diff(letters).toList match {
      case Nil => "NULL"
      case x => x.sorted.mkString
    }
  }
}
