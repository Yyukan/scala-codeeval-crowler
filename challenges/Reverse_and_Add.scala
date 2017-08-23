

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    reverseAndAdd(line, 1)
  }

  def isPalindrome(number: String):Boolean = number == number.reverse

  def reverseAndAdd(number: String, iteration: Int): Unit = {
    if (iteration == 100) return

    val x:Int = number.toInt + number.reverse.toInt
    if (isPalindrome(x.toString)) {
      println(iteration + " " + x)
    }
    else
      reverseAndAdd(x.toString, iteration + 1)
  }
}
