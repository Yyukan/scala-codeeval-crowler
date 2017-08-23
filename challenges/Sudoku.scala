import scala.collection.mutable
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(";") match {
      case Array(a, b) =>
        val sudoku = Sudoku(a.toInt, b.split(",").map(_.toInt).toList)
        //println(sudoku)
        println(sudoku.isValid.toString.capitalize)
    }
  }

  class Sudoku(val size:Int, val board: List[Int]) {

    def rowAt(row: Int):List[Int] = {
      val seq = for {
        i <- 0 to size - 1
      } yield board(size * row + i)
      seq.toList
    }

    def colAt(col: Int):List[Int] = {
      val seq = for {
        i <- 0 to size - 1
      } yield board(size * i + col)
      seq.toList
    }

    def regionAt(x: Int):List[Int] = {

      val regionsize = size match {
        case 4 => 2
        case 9 => 3
      }

      val seq =
      for {
        i <- 0 to regionsize - 1
        j <- 0 to regionsize - 1
      } yield cellAt(i + (x / regionsize) * regionsize, j + (x % regionsize) * regionsize)

//      println(s"$x $seq")
      seq.toList
    }

    def cellAt(x: Int, y:Int):Int = {
      board(x * size + y)
    }

    override  def toString = {
      val result = new StringBuilder
      for (i <- 0 to size - 1) {
        for (j <- 0 to size - 1) {
          val value =  cellAt(i, j)
          result.append(value + " ")

        }
        result.append("\n")
      }
      result.toString()
    }

    def isValid:Boolean = {
      val expected = (1 to size).toList
      for (i <- 0 to size - 1) {
        val row = rowAt(i)
        val column = colAt(i)
        val region = regionAt(i)

        if (row.sorted != expected) return false
        if (column.sorted != expected) return false
        if (region.sorted != expected) return false
      }

      true
    }
  }

  object Sudoku {
     def apply(size: Int, board: List[Int]): Sudoku = {
       new Sudoku(size, board)
     }
  }

}
