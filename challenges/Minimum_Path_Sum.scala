import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  while(lines.hasNext) {
    val dataLines = lines.next().toInt

    val matrix = new Matrix(dataLines)

    for (i <- 1 to dataLines) {
      matrix.add(lines.next().split(",").map(_.toInt))
    }

    println(matrix.minSum())
  }
}

case class Matrix(dimension: Int) {

  var matrix: Array[Int] = Array()

  def add(row: Seq[Int]):Unit = {
    matrix = matrix ++ row
  }

  def minSum():Int = {
    val n = dimension

    for (slice <- 2 * n - 2 to 0 by -1) {
      val z = if (slice < n)  0 else slice - n + 1
      for (j <- z to slice - z) {
        set(j, slice - j, minimum(at(j, slice - j), at(j + 1, slice - j), at(j, slice - j + 1)))
      }
    }
    at(0, 0)
  }

  def minimum(a: Int, b: Int, c:Int):Int = {
    if (b == Integer.MAX_VALUE) {
      if (c == Integer.MAX_VALUE) a
      else c + a
    } else {
      if (c == Integer.MAX_VALUE) b + a
      else (a + b) min (a + c)
    }
  }

  def at(row: Int, column:Int) = {
    if (row >= dimension || column >= dimension) Integer.MAX_VALUE
    else matrix(row * dimension + column)
  }

  def set(row: Int, column:Int, value:Int) = {
    matrix(row * dimension + column) = value
  }

  def show():Unit = {
    for (i <- 0 until dimension) {
      for (j <- 0 until dimension) print(at(i,j) + " ")
      println()
    }
  }
}

