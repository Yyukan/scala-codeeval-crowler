/*Sample code to read in test cases: */
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (line <- lines) {
    println(bits(line))
  }

  def bits(line: String):Int = {
    line.toInt.toBinaryString.foldLeft(0)((acc, c: Char) => 
      c match {
        case '1' => acc + 1
        case '0' => acc
    })
  }
}
