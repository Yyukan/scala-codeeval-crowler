/**
Game of life

The challenge is based on John’s Conway ‘Game of Life’. The Game of Life is a cellular automaton developed by the
British mathematician John Conway in 1970. The universe of the game is an infinite two-dimensional orthogonal
grid of square cells, each of which is in one of two possible states, alive or dead. E
very cell that is horizontally, vertically, or diagonally adjacent interacts with its eight neighbors.
At each step in time, the following iterations occur:

  Any live cell with fewer than two live neighbors dies, as if caused by under-population.
  Any live cell with two or three live neighbors lives on to the next generation.
  Any live cell with more than three live neighbors dies, as if by overcrowding.
  Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules
simultaneously to every cell in the seed — births and deaths occur simultaneously.
The rules continue to be applied repeatedly to create further generations.

  You have a grid of size N×N, seeded with some live cells. Your task is to determine the state of the grid after 10 iterations.

INPUT SAMPLE:

  The first argument is a file with the initial state of the grid. Alive cells are shown as asterisks ‘*’, and dead
cells are shown as points ‘.’. E.g.

.........*
.*.*...*..
..........
..*.*....*
.*..*...*.
.........*
..........
.....*..*.
.*....*...
.....**...
  */
object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  val universe = Grid(lines.size)

  for (y <- 0 until lines.size) {
    val line = lines(y)
    for (x <- 0 until line.length) {
      universe.init(Cell(line(x), x, y))
    }
  }

  for (i <- 1 to 10) {
    universe.permutate()
  }

  universe.draw()
}

/**
 * Represents universe grid as one-dimensional array
 */
case class Grid(size: Int) {
  val grid: Array[Cell] = new Array[Cell](size * size)
  /** store for next permutation */
  val next: Array[Cell] = new Array[Cell](size * size)

  def init(cell: Cell): Unit = set(grid, cell)

  def set(array: Array[Cell], cell: Cell): Unit = {
    array.update(cell.x + cell.y * size, cell)
  }

  def get(x: Int, y: Int): Cell = grid(x + y * size)

  def draw(): Unit = {
    for (i <- 0 until size) {
      for (j <- 0 until size) {
        print(get(j, i))
      }
      println()
    }
  }

  def permutate(): Unit = {
    for {
      y <- 0 until size
      x <- 0 until size
    } yield {
      set(next, mutate(get(x, y)))
    }
    // copy result of permutation
    next.copyToArray(grid)
  }

  def mutate(cell: Cell): Cell = {
    neighbours(cell) match {
      case x if x < 2 => if (cell.alive) cell.die() else cell.copy()
      case x if x > 3 => if (cell.alive) cell.die() else cell.copy()
      case x if x == 3 => if (!cell.alive) cell.live() else cell.copy()
      case _ => cell.copy()
    }
  }

  /**
   * Returns number of live neighbours around
   * horizontally, vertically, or diagonally
   */
  def neighbours(cell: Cell): Int = {
    val x = cell.x
    val y = cell.y

    var n = 0
    n = n + isAlive(x - 1, y - 1)
    n = n + isAlive(x, y - 1)
    n = n + isAlive(x + 1, y - 1)
    n = n + isAlive(x - 1, y)
    n = n + isAlive(x + 1, y)
    n = n + isAlive(x - 1, y + 1)
    n = n + isAlive(x, y + 1)
    n = n + isAlive(x + 1, y + 1)
    n
  }

  def isAlive(x: Int, y: Int): Int = {
    if (x < 0 || x >= size) 0
    else if (y < 0 || y >= size) 0
    else if (get(x, y).alive) 1 else 0
  }
}

/**
 * Represents cell in the grid with state
 * dead or alive
 */
case class Cell(alive: Boolean, x : Int, y: Int) {
  /** creates new live cell */
  def live(): Cell = Cell(alive = true, x, y)
  /** creates new died cell */
  def die(): Cell = Cell(alive = false, x, y)

  override def toString = if (alive) "*" else "."
}

case object Cell {
  def apply(char: Char, x: Int, y: Int): Cell = {
    char match {
      case '*' => Cell(alive = true, x, y)
      case '.' => Cell(alive = false, x, y)
    }
  }
}

