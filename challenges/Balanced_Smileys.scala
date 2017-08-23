import scala.util.control.Breaks._

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(isBalanced(line))
  }
  def isBalanced(message:String):String = {
    var minOpen = 0
    var maxOpen = 0

    breakable {
      for ( i <- 0 to message.length - 1) {
      message(i) match {
        case '(' =>
          maxOpen += 1
          if (i == 0 || message(i-1) != ':')
            minOpen += 1
        case ')' =>
          minOpen = 0.max(minOpen - 1)
          if (i == 0 || message(i-1) != ':')
            maxOpen -= 1
          if (maxOpen < 0)
            break()
        case _ =>
      }
     }
    }

    if (maxOpen >= 0 && minOpen == 0) "YES" else "NO"
  }

}
