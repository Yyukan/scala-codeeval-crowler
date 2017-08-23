import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(" ") match {
      case Array(numbers, pattern) =>
        println(calculate(numbers, pattern))
    }
  }

  def calculate(numbers: String, pattern: String):Long = {
    val (left, right) =  pattern.split("[-+]").map(_.size) match {
      case Array(a, b) => (a, b)
    }

    val (operand1, operand2) = (numbers.take(left), numbers.takeRight(right))

    pattern.contains('-') match {
      case true => operand1.toLong - operand2.toLong
      case false => operand1.toLong + operand2.toLong
    }
  }
}

