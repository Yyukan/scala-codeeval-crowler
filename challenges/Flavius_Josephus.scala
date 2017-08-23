import scala.collection.mutable
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {

    line.split(",") match {
      case Array(a, b) =>
        josephus(a.toInt, b.toInt)
    }
  }

  def josephus(n:Int, k:Int):Unit = {

    val killed = mutable.ListBuffer[Int]()

    def aux(numbers: mutable.Buffer[Int],n: Int, k: Int, startingPoint:Int):Int = {
      if(n == 1) return 1

      val newSp:Int = (startingPoint + k - 2) % n + 1

      killed.append(numbers.remove(newSp - 1))

      val survivor = aux(numbers, n - 1, k, newSp)
      if (survivor < newSp) {
        survivor
      } else
        survivor + 1
    }
    val numbers = (0 to n - 1).toBuffer
    aux(numbers, n, k, 1)
    killed.append(numbers(0))
    println(killed.mkString(" "))
  }

}
