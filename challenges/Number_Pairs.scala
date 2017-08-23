object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
     line.split(";") match {
       case Array(a, b) => {
         val n = b.toInt
         val numbers = a.split(",").map(_.toInt).toList
         numberPairs(numbers, n)
       }
     }
  }

  def numberPairs(numbers:List[Int], n:Int):Unit = {
    val vector = for {
      i <- 0 to numbers.length - 2
      j <- i + 1 to numbers.length - 1
    } yield (numbers(i), numbers(j))

    vector.filter(x => x._1 + x._2 == n).toList match {
      case Nil => println("NULL")
      case list => println(list.map(x => x._1 + "," + x._2).mkString(";"))
    }
  }
}
