/*Sample code to read in test cases:*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (l <- lines) {
    println(l.split(",").map(_.toInt) match {
      case Array(n, p1, p2) => {
        val x = 1 << (p1 - 1)
        val y = 1 << (p2 - 1)
        ((n & x) == x) == ((n & y) == y)
      }
    })
  }
}
