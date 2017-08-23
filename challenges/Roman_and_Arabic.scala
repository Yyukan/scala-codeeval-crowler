object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  val base = Map('I' -> 1, 'V' -> 5, 'X' -> 10, 'L' -> 50, 'C' -> 100, 'D' -> 500, 'M' -> 1000)

  for (line <- lines) {
    println(romanAndArabic(line))
  }

  def romanAndArabic(text: String): Int = {
    val list: List[Int] = text.map {
      case arabic if arabic.isDigit => arabic.asDigit
      case roman if roman.isLetter => base(roman)
    }.toList ::: List[Int](0, 0)

    list.sliding(4, 2).foldLeft(0)((accumulator: Int, element:List[Int]) => {
      if (element(1) >= element.last) {
        accumulator + element.head * element(1)
      } else {
        accumulator - element.head * element(1)
      }
    })
  }

}
