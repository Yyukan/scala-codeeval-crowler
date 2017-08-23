
object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(game(line))
  }

  def game(line: String):String = {
    line.split(" ") match {
      case Array(a, b, n) => {
        (1 to n.toInt).map(x => x match {
          case _ if x % a.toInt == 0 && x % b.toInt == 0 => "FB"
          case _ if x % a.toInt == 0 => "F"
          case _ if x % b.toInt == 0 => "B"
          case z => z.toString
        }).mkString(" ")
      }
    }

  }

}