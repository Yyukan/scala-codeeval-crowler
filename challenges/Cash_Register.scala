object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  val coins = Map(1 -> "PENNY", 5 -> "NICKEL", 10 -> "DIME", 25 -> "QUARTER", 50 -> "HALF DOLLAR")
  val cash = Map(1 -> "ONE", 2 -> "TWO", 5 -> "FIVE", 10 -> "TEN", 20 -> "TWENTY", 50 -> "FIFTY", 100 -> "ONE HUNDRED")

  for (line <- lines) {
    line.split(";") match {
      case Array(a, b) => {
        val price = a.toFloat
        val cash = b.toFloat

        cash - price match {
          case x if x == 0 => println("ZERO")
          case x if x < 0 => println("ERROR")
          case x => println(change(x))
        }
      }
    }
  }

  def change(x : Float): String = {

    def auxd(number:Int):List[String] = {
      number match {
        case _ if number >= 100 => cash(100) :: auxd(number - 100)
        case _ if number >= 50 => cash(50) :: auxd(number - 50)
        case _ if number >= 20 => cash(20) :: auxd(number - 20)
        case _ if number >= 10 => cash(10) :: auxd(number - 10)
        case _ if number >= 5 => cash(5) :: auxd(number - 5)
        case _ if number >= 2 => cash(2) :: auxd(number - 2)
        case _ if number > 0 => cash(1) :: auxd(number - 1)
        case 0 => Nil
      }
    }

    def auxc(number:Int):List[String] = {
      number match {
        case _ if number >= 50 => coins(50) :: auxc(number - 50)
        case _ if number >= 25 => coins(25) :: auxc(number - 25)
        case _ if number >= 10 => coins(10) :: auxc(number - 10)
        case _ if number >= 5 => coins(5) :: auxc(number - 5)
        case _ if number > 0 => coins(1) :: auxc(number - 1)
        case 0 => Nil
      }
    }

    def aux(dollars: Int, cents: Int): List[String] = {
      auxd(dollars) ++ auxc(cents)
    }

    "%.2f".format(x).split("\\.") match {
      case Array(a, b) => {
        val dollars = a.toInt
        val cents = b.toInt
        aux(dollars, cents).mkString(",")
      }
      case _ => println("Error change  " + x); ""
    }
  }
}
