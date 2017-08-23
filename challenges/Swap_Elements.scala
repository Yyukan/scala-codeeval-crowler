/*Sample code to read in test cases:*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
 for (line <- lines) {
    println(swapList(line))
  }

  def swapList(line:String):String = {
    line.split(":") match {
      case Array(numbers, positions) =>
      {
        val position = asPositions(positions)

        var result:List[Int] = asNumbers(numbers)
        position foreach { p =>
          result = swap(result, p)
        }

        result.mkString(" ")
      }
    }
  }

  def asNumbers(list: String): List[Int] = list.trim.split(" ").map(_.toInt).toList

  def asPositions(string: String): List[(Int, Int)] = {
    string.trim.split(",").map( s =>
      s.trim.split("-") match {
        case Array(first, second) => (first.toInt, second.toInt)
      }).toList
  }

  def swap(list: List[Int], position: (Int, Int)):List[Int] =
    list.updated(position._1, list(position._2)).updated(position._2, list(position._1))

}