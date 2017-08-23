import scala.collection._

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val numbers = line.split(" ").map(_.toInt).toList

    cycle(numbers) match {
      case Some((from, to)) => println(numbers.drop(from).dropRight(numbers.size - to).mkString(" "))
      case None =>
    }
  }

  def cycle(numbers: List[Int]): Option[(Int, Int)] = {
    val visitedToIndex = mutable.Map[Int, Int]()

    for (i <- 0 to numbers.length - 1) {
      visitedToIndex.get(numbers(i)) match {
        case None => visitedToIndex += (numbers(i) -> i)
        case Some(index) => return Some((index, i))
      }
    }
    None
  }
}