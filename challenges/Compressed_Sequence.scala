import scala.collection._
import scala.collection.mutable.ListBuffer

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val numbers = line.split(" ").map(_.toInt)

    var count = 0
    var previous = numbers.head

    val result: ListBuffer[Int] = mutable.ListBuffer[Int]()

    numbers.foreach( n => {
      if (n != previous) {
        result.append(count)
        result.append(previous)
        previous = n
        count = 1
      } else {
        count +=1
      }
    })
    result.append(count)
    result.append(previous)

    println(result.mkString(" "))
  }

}



