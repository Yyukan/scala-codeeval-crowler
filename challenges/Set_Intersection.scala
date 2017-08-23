/*Sample code to read in test cases: */
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (line <- lines) {
    println(intersect(line))
  }

  def intersect(line: String):String = {
    line.split(";") match {
      case Array(list1, list2) => {
        val listInt1:List[Int] = list1.split(",").toList.map(_.toInt)
        val listInt2:List[Int] = list2.split(",").toList.map(_.toInt)

        listInt1.intersect(listInt2).mkString(",")
      }
    }
  }
}
