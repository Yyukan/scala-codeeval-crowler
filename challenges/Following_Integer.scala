object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  for (line <- lines) {
    val numbers = line.permutations.flatMap(x => insert(x, 1) :+ x)
    println(numbers.toList.map(_.toInt).filter(_ > line.toInt).sorted.head)
  }

  def insert(string: String, index: Int) : List[String] = {
    if (index > string.length ) return Nil

    val number = string.substring(0, index) + '0' + string.substring(index, string.length)

    number :: insert(string, index + 1)
  }

}