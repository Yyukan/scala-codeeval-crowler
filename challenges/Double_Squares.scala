object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines.drop(1)) {
    val number = line.toInt

    var count = 0
    for (i:Int <- 0 to Math.sqrt(number / 2).toInt) {
      val j:Double = Math.sqrt(number - i * i)
      if (Math.floor(j) == j) count+=1
    }
    println(count)
  }
}