/*Sample code to read in test cases:*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (line <- lines) {
    println(fibonacci(line.toInt))
  }

  // F(0) = 0; F(1) = 1; F(n) = F(n-1) + F(n-2) when n>1
  def fibonacci(n: Int):Int = n match {
    case 0 => 0
    case 1 => 1
    case _ => fibonacci(n - 1) + fibonacci(n - 2)
  }

}