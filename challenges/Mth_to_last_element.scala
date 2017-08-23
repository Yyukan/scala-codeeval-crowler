/*Sample code to read in test cases: */
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
 for (line <- lines) {
    last(line)
  }

  def last(line: String):Unit = {
    val splited = line.split(" ").toList.reverse

    val index = splited.head.toInt
    val list = splited.tail

    if (index <= list.length) println(list(index - 1))
  }
}
