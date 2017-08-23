import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(";") match {
      case Array(counts, numbers) =>
        val array: Array[Int] = numbers.split(",").map(_.toInt)

        val seen = mutable.HashSet[Int]()
        array foreach {
            case x if seen(x) => println(x)
            case x            => seen += x
        }
    }
  }

}
