/*Sample code to read in test cases:*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val list = line.split(" ").map(_.toInt).toList

    val result = list.groupBy(x => x).values.filter(_.length == 1).flatten

    result match {
      case Nil => println(0)
      case x => println(list.indexOf(x.min) + 1)
    }
  }

}