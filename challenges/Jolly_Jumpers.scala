object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val numbers = line.split(" ").map(_.toInt).toList
    numbers match {
      case Nil => println("Not jolly")
      case head :: Nil => println("Not jolly")
      case head :: tail :: Nil => println("Jolly")
      case head :: tail => jolly(head, tail)
    }
  }

  def jolly(n:Int, list: List[Int]):Unit = {
    val sums = list.sliding(2).map((x: List[Int]) => math.abs(x.head - x.tail.head)).toSet

    val expected = (1 to n - 1).reverse.toSet
    if (expected.diff(sums).size == 0) println("Jolly")
    else println("Not jolly")
  }
}
