import scala.collection.mutable
import scala.collection.mutable.{ HashMap, MultiMap, Set }

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val data = line.split(" ").map(_.toInt)
    val friends = data.head
    val houses = data.tail.sorted
    val variants = (houses.head to houses.last).toSet

    friends match {
      case 0 | 1 => println(0)
      case _ =>
        println(variants.foldLeft(Integer.MAX_VALUE) { (accumulator, element) =>
          var sum = 0
          houses.foreach { x =>
            sum += Math.abs(element - x)
          }
          if (accumulator > sum) sum else accumulator
        })
    }
  }
}

