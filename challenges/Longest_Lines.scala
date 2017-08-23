/*Sample code to read in test cases: */
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  val number = lines.next().toInt
  lines.toList.sortBy(-_.length).take(number).foreach(println(_))
}
