import scala.collection.immutable.TreeMap
import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  val months = Map("Jan" -> 1, "Feb" -> 2, "Mar" -> 3, "Apr" -> 4, "May" -> 5,
    "Jun" -> 6, "Jul" -> 7, "Aug" -> 8, "Sep" -> 9, "Oct" -> 10, "Nov" -> 11, "Dec" -> 12)

  for (line <- lines) {

    val expirience = mutable.Set[Entry]()

    val periods:List[(Entry, Entry)] = line.split(";").map { x =>
      x.trim.split("-") match {
        case Array(a, b) => (toEntry(a), toEntry(b))
      }
    }.toList

    periods.foreach { x =>

      for (year:Int <- x._1.year to x._2.year) {
        val startMonth = if (year == x._1.year) x._1.month else 1
        val endMonth = if(year == x._2.year) x._2.month else 12
        for (month: Int <- startMonth to endMonth) {
          expirience += Entry(month, year)
        }
     }
    }

    println(expirience.size / 12)
  }

  def toEntry(string: String):Entry = {
    string.split(" ") match {
      case Array(a, b) => Entry(months(a), b.toInt)
    }
  }

}

case class Entry(month: Int, year: Int)




