/*Sample code to read in test cases:*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (l <- lines) {
      println(l.split(" ").map(_.toFloat).sorted.
            map("%.3f".format(_)).mkString(" "))
  }
}
