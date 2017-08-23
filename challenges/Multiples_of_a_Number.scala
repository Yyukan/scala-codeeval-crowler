object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(",") match {
      case Array(x, n) => {
        println(multiply(x.toInt, n.toInt, n.toInt))
      }
    }
  }

  def multiply(x:Int, n:Int, next:Int):Int = {
    if (next >= x) return next
    multiply(x, n, next + n)
  }
}