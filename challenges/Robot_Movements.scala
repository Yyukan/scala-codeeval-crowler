object Main extends App {

  //val source = scala.io.Source.fromFile(args(0))
  //val lines = source.getLines.filter(_.length > 0).toList

  val width = 4
  val height = 4

  val grid = Array.ofDim[Boolean](width, height)


  println(paths(grid, 0, 0, width, height))

  def paths(grid: Array[Array[Boolean]], x: Int, y: Int, width: Int, height: Int) : Int = {
    if (grid(x)(y)) return 0                        // already visited
    if (x == width - 1 && y == height - 1) return 1 // reached bottom right corner

    var path = 0

    grid(x)(y) = true // keep tracking

    if (x > 0) { // go left
      path += paths(grid, x - 1, y, width, height)
    }
    if (x < width - 1) {  // go right
      path += paths(grid, x + 1, y, width, height)
    }
    if (y > 0) {  // go up
      path += paths(grid, x, y - 1, width, height)
    }
    if (y < height - 1) { // go down
      path += paths(grid, x, y + 1, width, height)
    }

    grid(x)(y) = false

    path
  }

}