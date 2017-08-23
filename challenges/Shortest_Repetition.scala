
object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(repetition(line))
  }

  def repetition(line: String) = {
    val result = scala.collection.mutable.MutableList[Int]()
    for(i <- 1 to line.length / 2) {
      if (hasPeriod(line.grouped(i).toList, i)) {
        result += i
      }
    }
    result += line.length
    result.min
  }

  def hasPeriod(list: List[String], i:Int):Boolean = {
    list.forall(e => e.length == i) && list.forall(e => e == list(0))
  }
}