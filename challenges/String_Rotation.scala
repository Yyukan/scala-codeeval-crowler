
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(",") match {
      case Array(s1, s2) =>
        println(isRotation(s1, s2).toString.capitalize)
      }
  }

  def shift(list: List[Char]):List[Char] = list.tail :+ list.head


  def isRotation(source: String, check:String):Boolean = {

    var tmp = check
    check.foreach(x => {
      if (tmp == source) return true
      tmp = shift(tmp.toList).mkString
    })
    false
  }
}
