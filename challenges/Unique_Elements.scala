/*Sample code to read in test cases: */
object Main extends App { 
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (line <- lines) {
    println(line.split(",").distinct.mkString(","))
  }
}
