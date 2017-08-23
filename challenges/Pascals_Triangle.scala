

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val triangle = pascalStream(List(1))

    println(triangle.take(line.toInt).flatten.mkString(" "))
  }

  def addList(l1: List[Int], l2: List[Int]): List[Int] = {
    (l1 zip l2) map { case (x, y) => x + y }
  }

  def shiftRight(row: List[Int]): List[Int] = 0::row
  def shiftLeft(row: List[Int]): List[Int] = row:::List(0)

  def pascalStream(row: List[Int]): Stream[List[Int]] =
    Stream.cons(row, pascalStream(addList(shiftLeft(row), shiftRight(row))))
}
