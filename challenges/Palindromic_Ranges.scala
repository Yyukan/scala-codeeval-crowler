import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(" ") match {
      case Array(a, b) => {
        val L = a.toInt
        val R = b.toInt

        val result = for {
          i <- L to R
          j <- i to R
        } yield isEvenNumberOfPalindroms(i, j)

        println(result.count(_ == true))
      }
    }
  }

  def isPalindrom(n :Int) = n.toString.reverse == n.toString

  def isEvenNumberOfPalindroms(a : Int, b : Int) = {
    var counter = 0
    for (i <- a to b)  {
      if (isPalindrom(i)) counter += 1
    }
    counter % 2 == 0
  }
}


