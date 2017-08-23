object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(",") match {
      case Array(a, b) =>
        val n = a.toInt
        val m = b.toInt
        println(primes(integers(2)).takeWhile(_ <= m).count(_ >=n))
    }
  }

  def primes(numbers: Stream[Int]): Stream[Int] =
    Stream.cons(numbers.head, primes(numbers.tail.filter(x => x % numbers.head != 0)))

  def integers(n: Int): Stream[Int] = Stream.cons(n, integers(n + 1))
}
