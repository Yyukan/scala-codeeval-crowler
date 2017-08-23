import scala.collection.mutable

/**
 * CARD NUMBER VALIDATION
CHALLENGE DESCRIPTION:

To check whether a bank card number is valid or it is it just a set of random numbers, Luhn formula is used.

The formula verifies a number against its included check digit, which is usually appended to a partial account number to generate the full account number. This account number must pass the following test:

From the rightmost digit, which is the check digit, moving left, double the value of every second digit;
if the product of this doubling operation is greater than 9 (for example, 7×2=14), then sum the digits
of the products (for example, 12:1+2=3, 14:1+4=5).
Take the sum of all the digits.
If the total modulo 10 is equal to 0 (if the total ends in zero) then, according to the Luhn formula, the number is valid; otherwise, it is not valid.
Examples of formula calculation and result checking:

Checking number 1556 9144 6285 339
 */

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val numbers = line.split(" ").mkString.map(_.asDigit)

    numbers.reverse.zipWithIndex.map(luhn).sum match {
      case valid if valid % 10 == 0 => println(1)
      case _ => println(0)
    }
  }

  def luhn(x : (Int, Int)) : Int = {
    if (x._2 % 2 == 0) x._1
    else if (x._1 * 2 < 9) x._1 * 2 else
      (x._1 * 2).toString.map(_.asDigit).sum
  }
}


