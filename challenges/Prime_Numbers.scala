object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(primes(integers(2)) takeWhile( _ < line.toInt) mkString ",")
  }

  def primes(numbers: Stream[Int]): Stream[Int] =
    Stream.cons(numbers.head, primes(numbers.tail.filter(x => x % numbers.head != 0)))

  def integers(n: Int): Stream[Int] = Stream.cons(n, integers(n + 1))
}
