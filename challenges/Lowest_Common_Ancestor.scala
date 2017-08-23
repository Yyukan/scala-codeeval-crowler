import scala.collection.mutable
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  val tree = Map[Int, Int](30 -> 0, 8 -> 30,52 -> 30, 3 -> 8, 20 -> 8, 10 -> 20, 29 -> 20)

  for (line <- lines) {
    line.split(" ") match {
      case Array(a, b) =>
        val n = a.toInt
        val m = b.toInt

        println((n :: path(n) intersect m :: path(m)).head)
    }
  }

  def path(n : Int): List[Int] = {
    tree(n) match {
      case 0 => n :: List()
      case x => x :: path(x)
    }
  }
}
