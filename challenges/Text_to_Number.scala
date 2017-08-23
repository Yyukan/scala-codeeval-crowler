
object Main extends App {

  val toDigit: Map[String, Int] = Map(
    "zero" -> 0, "one" -> 1, "two" -> 2, "three" -> 3, "four" -> 4,
    "five" -> 5, "six" -> 6, "seven" -> 7, "eight" -> 8, "nine" -> 9,
    "ten" -> 10, "eleven" -> 11, "twelve" -> 12, "thirteen" -> 13,
    "fourteen" -> 14, "fifteen" -> 15, "sixteen" -> 16, "seventeen" -> 17,
    "eighteen" -> 18, "nineteen" -> 19,
    "twenty" -> 20, "thirty" -> 30, "forty" -> 40,
    "fifty" -> 50, "sixty" -> 60, "seventy" -> 70, "eighty" -> 80,
    "ninety" -> 90,
    "hundred" -> 100,
    "thousand" -> 1000,
    "million" -> 1000000
  )

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(textToNumber(line))
  }

  def textToNumber(line: String): Int = {

    def toNumber(list: List[String], number: Int, million: Int, thosand: Int, hundred: Int, sign: Int): Int = {
      list match {
        case Nil => sign * (1000000 * million + 1000 * thosand + 100 * hundred + number)
        case head :: tail =>
          head match {
            case "negative" => toNumber(tail, number, million, thosand, hundred,  -1)
            case "million" => toNumber(tail, 0, number + thosand * 1000 + hundred * 100, 0, 0, sign)
            case "thousand" => toNumber(tail, 0, million, number + hundred * 100, 0, sign)
            case "hundred" => toNumber(tail, 0, million, thosand, number, sign)
            case _ => toNumber(tail, number + toDigit(head), million, thosand, hundred, sign)
          }
      }
    }

    toNumber(line.split(" ").toList, 0, 0, 0, 0, 1)
  }
}

