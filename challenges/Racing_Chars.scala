import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)


  var car = -1

  for (line <- lines) {
     line.contains('C') match {
       case true =>  car = move(line, 'C')
       case false => car = move(line, '_')
     }
  }

  def move(line: String, through: Char):Int = {
    val position = line.indexOf(through)
    position match {
      case x if position < car => println(line.replace("" + through, "/"))
      case x if position > car => if (car == -1)
          println(line.replace("" + through, "|"))
        else
          println(line.replace("" + through, "\\"))
      case x if position == car => println(line.replace("" +through, "|"))
    }
    position
  }
}

