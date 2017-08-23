
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  val numbers = Map(0 -> "", 1 -> "One", 2 -> "Two", 3 -> "Three",
    4 -> "Four", 5 -> "Five", 6 -> "Six", 7 -> "Seven", 8 -> "Eight", 9 -> "Nine",
    10 -> "Ten", 11 -> "Eleven",
    12 ->    "Twelve", 20 ->	"Twenty",
    13 ->	"Thirteen", 30 ->	"Thirty",
    14 ->	"Fourteen",	40 ->	"Forty",
    15 ->	"Fifteen", 	50 ->	"Fifty",
   	16 ->	"Sixteen",	60 ->	"Sixty",
    17 ->	"Seventeen", 70 -> "Seventy",
    18 ->	"Eighteen",	80 ->	"Eighty",
  	19 ->	"Nineteen",	90 ->	"Ninety",
    100 -> "Hundred",
    1000 -> "Thousand",
    1000000 -> "Million",
    1000000000 -> "Billion"
  )

  for (line <- lines) {
    println(toText(line))
  }

  def toText(line:String): String = {
    def toText(number: Int): String = {
      number match {
        case x if number / 1000000000 != 0 => toText(number / 1000000000) + numbers(1000000000) + toText(number % 1000000000)
        case x if number / 1000000 != 0 => toText(number / 1000000) + numbers(1000000) + toText(number % 1000000)
        case x if number / 1000 != 0 => toText(number / 1000) + numbers(1000) + toText(number % 1000)
        case x if number / 100 != 0 => toText(number / 100) + numbers(100) + toText(number % 100)
        case x if number / 10 >= 2 => numbers(number / 10 * 10) + toText(number % 10)
        case x if number < 20 => numbers(number)
        case _ => ""
      }
    }

    toText(line.toInt) + "Dollars"
  }
}
