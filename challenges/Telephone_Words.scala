
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  val numbers = Map ('0' -> "0",
  '1' -> "1",
  '2' -> "abc",
  '3' -> "def",
  '4' -> "ghi",
  '5' -> "jkl",
  '6' -> "mno",
  '7' -> "pqrs",
  '8' -> "tuv",
  '9' -> "wxyz")
  
  for (line <- lines) {
    val a = for {
      a <- numbers(line(0))
      b <- numbers(line(1))
      c <- numbers(line(2))
      d <- numbers(line(3))
      e <- numbers(line(4))
      f <- numbers(line(5))
      g <- numbers(line(6))
    } yield "" + a + b + c + d + e + f + g
    println(a.sorted.mkString(","))
  }

}
