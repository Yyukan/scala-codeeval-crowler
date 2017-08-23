object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  def toNumbers(line:String) = line.trim.split(" ").map(_.toInt).toList
  def toMaxes(list: List[Int]) = list.sliding(2).map(_.max).toList

  // go from bottom to the top of the triangle
  var maxes = List[Int]()
  for (line <- lines.toSeq.reverseIterator) {
    // read line and transform to list of the numbers
    val numbers = toNumbers(line)

    if (maxes.isEmpty)
      maxes = numbers
    else {
      // new maxes is zip of previous and numbers
      maxes = (maxes zip numbers) map { case (a, b) => a + b }
    }
    // create list of max of adjacent numbers
    maxes = toMaxes(maxes)
  }

  println(maxes.head)
}

