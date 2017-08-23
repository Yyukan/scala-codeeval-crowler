/*Sample code to read in test cases:*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (l <- lines) {
      l.split(",") match {
          case Array(s1, s2) => 
             val x = s1.toInt
             val y = s2.toInt
             println(x - (x / y) * y)
      }
  }
}
