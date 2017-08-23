/*Sample code to read in test cases: */
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (l <- lines) {
    println(Integer.parseInt(l) match {
        case even if even % 2 == 0 => 1
        case _ => 0
    })
  }
}
