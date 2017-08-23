object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val sum = line.toInt
    val five = sum / 5
    val three = (sum % 5) / 3
    val one = (sum % 5) % 3

    println(five + three + one)
  }
}