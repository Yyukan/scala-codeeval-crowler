/*Sample code to read in test cases:*/
object Main extends App {
  def primeStream(s: Stream[Int]): Stream[Int] =
    Stream.cons(s.head, primeStream(s.tail filter { _ % s.head != 0 }))
  
  val primes = primeStream(Stream.from(2))
  println(primes take 1000 sum)
}
