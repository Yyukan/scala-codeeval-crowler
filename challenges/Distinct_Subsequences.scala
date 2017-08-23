import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(",") match {
      case Array(a,b) => println(aux(a, b, 0, 0))
    }
  }

  def aux(a: String, b: String, i: Int, j: Int):Int = {
    if (j == b.length) {
      if (i == a.length && a(i - 1) != b(j - 1)) return 0
      else return 1
    }

    if (i == a.length) return 0

    if (a(i) == b(j))
      aux(a, b, i + 1, j + 1) + aux(a, b, i + 1, j)
    else
      aux(a, b, i + 1, j)
  }
}

