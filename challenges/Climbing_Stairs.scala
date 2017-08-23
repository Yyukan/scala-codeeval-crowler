object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  for (line <- lines) {
    println(stairs(line.toInt))
  }

  // recursive solution
  def f(n: Int) : Long = {
    if (n == 1) 1L
    else if (n == 2) 2L
    else f(n - 1) + f(n - 2)
  }

  def stairs(n : Int): BigDecimal = {
    if (n == 0) return 0
    var a: BigDecimal = 1
    var b: BigDecimal = 1

    for (i <- 1 until n) {
      val c = a
      a = b
      b += c
    }
    b
  }

}