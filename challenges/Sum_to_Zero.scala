import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val numbers = line.split(",").map(_.toInt).toList
    println(sum4to0(numbers))
  }

  def sum4to0(numbers: List[Int]): Int = {
    numbers.size match {
      case x if x < 4 => 0
      case x if x == 4 => if (numbers.sum == 0) 1 else 0
      case x =>
        var count = 0

        for {
          i <- 0 until numbers.size
          j <- i + 1 until numbers.size
          k <- j + 1 until numbers.size
          l <- k + 1 until numbers.size
        } yield if (numbers(i) + numbers(j) + numbers(k) + numbers(l) == 0) count += 1

        count
    }

  }
}

