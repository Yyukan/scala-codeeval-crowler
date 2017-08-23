import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  val terminal = Terminal(10, 10)

  var control = false
  var row = -1
  var digit = false
  var caret = false

  for (line <- lines) {
    line foreach {
        case '^' =>
          if (control) {
            terminal.display('^')
            control = false
          }
          else control = true
        case symbol => {
          if (control) {
            symbol match {
              case 'h' => terminal.cursor = terminal.move(0, 0); control = false
              case 'c' => terminal.clear(); control = false
              case 'b' => terminal.cursor = (terminal.cursor._1, 0); control = false
              case 'd' => terminal.cursor = terminal.move(terminal.cursor._1 + 1, terminal.cursor._2); control = false
              case 'u' => terminal.cursor = terminal.move(terminal.cursor._1 - 1, terminal.cursor._2); control = false
              case 'l' => terminal.cursor = terminal.move(terminal.cursor._1, terminal.cursor._2 - 1); control = false
              case 'r' => terminal.cursor = terminal.move(terminal.cursor._1, terminal.cursor._2 + 1); control = false
              case 'e' => terminal.erase(); control = false
              case 'i' => terminal.mode = 0; control = false
              case 'o' => terminal.mode = 1; control = false
              case x if x.isDigit => {
                if (digit) {
                  digit = false
                  terminal.cursor = (row, symbol.asDigit)
                  control = false
                }
                else {
                  row = symbol.asDigit
                  digit = true
                }
              }
              case _ => control = false
            }
          } else {
            terminal.display(symbol)
          }
        }

      }
  }

  terminal.print()
}

case class Terminal(height: Int, width: Int) {

  val screen:Array[Array[Char]] = Array.ofDim[Char](height, width)

  var cursor:(Int, Int) = (0, 0)

  var mode = 1 // 0 = insert, 1 = overwrite

  def move(y:Int, x:Int):(Int, Int) = {
    if (x < 0) return (y, 0)
    if (y < 0) return (0, x)
    if (x == width) return (y, x - 1)
    if (y == height) return (y - 1, x)

    (y, x)
  }

  def erase(): Unit = {
    val (y, x) = cursor
    for (i <- x until width) {
      screen(y)(i) = ' '
    }
  }

  def display(symbol:Char) = {
    if (mode == 0) { // overwrite mode
      val (y:Int, x:Int) = cursor

      var rest = for {
        i <- x until width
      } yield screen(y)(i)

      for (i <- x + 1 until width) {
        screen(y)(i) = rest.head; rest=rest.tail
      }
    }
    screen(cursor._1)(cursor._2) = symbol
    cursor = move(cursor._1, cursor._2 + 1)
  }

  def clear():Unit = {
    for (i <- 0 until height; j <- 0 until width) {
      screen(i)(j) = ' '
    }
  }

  def print() = {
    for (i <- 0 until height) {
      println(screen(i).mkString)
    }
  }
}

