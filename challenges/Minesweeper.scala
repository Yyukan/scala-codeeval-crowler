import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(";") match {
      case Array(a, b) =>
        val (n, m) = a.split(",").map(_.toInt) match {
          case Array(x, y) => (x, y)
        }
        val board = Board(m, n, b)
        println(board.resolve)
    }
  }

  case class Board(n: Int, m: Int, board: List[Char]) {

    def asChar(x: Int) = (x + '0').toChar

    def cellAt(x: Int, y:Int):Char = {
      board(y * n + x)
    }

    def toXy(index: Int):(Int, Int) = {
      val x = index % n
      val y = index / n

      (x, y)
    }

    def check(x : Int, y: Int):Int = {
      if (x < 0 || y < 0) return 0
      if (x == n || y == m) return 0

      cellAt(x, y) match {
        case '*' => 1
        case _ => 0
      }
    }

    def mines(index: Int):Int = {
      val (x, y) = toXy(index)

      var count = 0
      count += check(x - 1, y - 1)
      count += check(x + 1, y + 1)
      count += check(x + 1, y)
      count += check(x - 1, y)
      count += check(x, y - 1)
      count += check(x, y + 1)
      count += check(x + 1, y - 1)
      count += check(x - 1, y + 1)

      count
    }

    def resolve:String = {
      val resolved = board.zipWithIndex map {
        case ('*', index) => '*'
        case ('.', index) => mines(index)
        case _ =>
      }

      resolved.grouped(n).map(_.mkString).mkString
    }

    override def toString:String = {
      board.grouped(n).map(_.mkString).mkString("\n")
    }
  }

  object Board {
    def apply(n: Int, m: Int, content: String):Board = {
      new Board(n, m, content.toList)
    }
  }
}

