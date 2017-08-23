/*Sample code to read in test cases:*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (line <- lines) {
    println(roadTrip(line))
  }

  def roadTrip(line: String):String = {
    val distance: List[Int] = line.split(";").map(x =>
      x.trim.split(",") match {
        case Array(a,b) => b
      }).toList.map(_.toInt).sorted

    val result = distance.head :: distance.zip(distance.tail).map(x => x._2 - x._1)
    result.mkString(",")
  }

}
