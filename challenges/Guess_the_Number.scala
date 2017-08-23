import scala.collection.mutable

/**
 * GUESS THE NUMBER
CHALLENGE DESCRIPTION:

Let’s play “Guess the number” game. This game is for two players, and it has simple rules:

one person (master) picks an integer in a certain range (for example, from 0 to 100 inclusive)
another person (you) tries to guess it in a certain number of attempts.
The task is facilitated by the fact that after each attempt, a master gives you a hint whether the number is higher or lower.

A reasonable approach to this game is to use the “divide and conquer” principle: in a range of numbers, always select a middle number. Then, based on the received response, discard the upper or the lower half of this range (together with the selected number) as it cannot contain the right answer. Now, the range of numbers is halved. Again, select the middle number. (If the number of integers in the range is even, select the greater one out of two middle numbers.) Repeat until you hear “Yay!” from a master — you guessed the number.

With this algorithm, you can win in at most log2 N attempts, where N is the range size.
 */

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val input = line.split(" ")
    val range = input.head.toInt
    val hints = input.tail

    println(guess(0, range, hints.toList))
  }

  def guess(lower : Int, upper: Int, hints : List[String]) : Int = {
    val range = upper - lower
    val middle = if (range % 2 == 0) range / 2 + lower else (range + 1) / 2 + lower
    hints.head match {
      case "Yay!" => middle
      case "Lower" => guess(lower, middle - 1,  hints.tail)
      case "Higher" => guess(middle + 1, upper, hints.tail)
    }
  }
}


