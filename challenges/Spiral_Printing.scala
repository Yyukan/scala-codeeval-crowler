object Sides extends Enumeration {
  type Sides = Value
  val LEFT, RIGHT, UP, DOWN = Value
}

object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  for (line <- lines) {
    line.split(";") match {
      case Array(n, m, data) =>
        val matrix = Matrix(n.toInt, m.toInt, data.split(" ").toList)
        matrix.spiral()
    }
  }
}


case class Matrix(rows : Int, columns: Int, data: List[String]) {
  import Sides._

  // return one-dimensional index
  def index(row : Int, column: Int) = row * columns + column

  // return matrix element
  def get(row : Int, column: Int) = data(index(row, column))

  // remove indexes from data
  def filterBy(indexes: Seq[Int]) : List[String] = {
    data.zipWithIndex.filterNot(x => indexes.contains(x._2)).map(_._1)
  }

  def isEmpty = data.isEmpty

  def printDirection(side: Sides):List[String] = {
    side match {
      case RIGHT => (0 until columns).map(get(0, _)).toList
      case LEFT => (columns - 1 to 0 by -1).map(get(rows - 1, _)).toList
      case UP => (rows - 1 to 0 by -1).map(get(_, 0)).toList
      case DOWN => (0 until rows).map(get(_, columns - 1)).toList
    }
  }

  def sliceSide(side: Sides) : Matrix = {
    side match {
      case UP => {
        Matrix(rows - 1, columns, filterBy((0 until columns).map(index(0, _))))
      }
      case DOWN => {
        Matrix(rows - 1, columns, filterBy((0 until columns).map(index(rows - 1, _))))
      }
      case LEFT => {
        Matrix(rows, columns - 1, filterBy(((rows - 1) to 0 by -1).map(index(_, 0))))
      }
      case RIGHT => {
        Matrix(rows, columns - 1, filterBy((0 until rows).map(index(_, columns - 1))))
      }
    }
  }

  def spiral() : Any = {
    def spiral(side: Sides, matrix: Matrix) : List[String] = {

      if (matrix.isEmpty) {
        return Nil
      }

      side match {
        case UP => matrix.printDirection(side) ++ spiral(RIGHT, matrix.sliceSide(LEFT))
        case DOWN => matrix.printDirection(side) ++ spiral(LEFT, matrix.sliceSide(RIGHT))
        case LEFT => matrix.printDirection(side) ++ spiral(UP, matrix.sliceSide(DOWN))
        case RIGHT => matrix.printDirection(side) ++ spiral(DOWN, matrix.sliceSide(UP))
      }
    }

    println(spiral(RIGHT, this).mkString(" "))
  }

  def draw() = {
    for (i <- 0 until rows) {
      for (j <- 0 until columns)
        print(s"${get(i, j)} ")
      println()
    }
  }
}