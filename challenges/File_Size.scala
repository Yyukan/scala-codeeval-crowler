/*Sample code to read in test cases:*/

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  println(source.size)
}
