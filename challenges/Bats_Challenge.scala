/*Sample code to read in test cases:
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (l <- lines) {
    // Do something with each non-blank line
  }
}
*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val data: List[Int] = line.split(" ").map(_.toInt).toList

    val wireLength: Int = data(0)
    val distance = data(1)
    val bats = data(2)

    bats match {
      case 0 => {
        println((wireLength - 12) / distance + 1)
      }
      case _ => println(maximumBats(wireLength, distance, bats, data.tail.tail.tail))
    }

  }

  def maximumBats(length: Int, distance: Int, bats: Int, indexes: List[Int]): Int = {

    def max(end: Int, begin: Int, distance: Int) = ((end - distance) - (begin + distance)) match {
      case x if x > 0 && x < distance => 1
      case x if x > 0 => x / distance + 1
      case _ => 0
    }

    var additional = 0
    var _data: List[Int] = indexes.sorted

    // added left border
    if (!_data.contains(6)) {
      if ((_data.head - 6) >= distance) {
        _data = List[Int](6) ::: _data
        additional += 1
      }
    }

    // added right border
    if (!_data.contains(length - 6)) {
      if ((length - 6) - _data.last >= distance) {
        _data = _data :+ (length - 6)
        additional += 1
      }
    }

    additional + _data.sorted.sliding(2).foldLeft(0)((accumulator, pair) => {
      accumulator + max(pair.last, pair.head, distance)
    })
  }

}
