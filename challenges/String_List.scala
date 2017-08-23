import scala.collection.immutable.IndexedSeq

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(",") match {
      case Array(n, alphabet) => {
        println(stringList(n.toInt, alphabet).toList.distinct.sorted.mkString(","))
      }
    }
  }

  def stringList(n: Int, alphabet: String):Iterable[String] = {
    if (n == 0) return List[String]("")

    alphabet.flatMap(c => stringList(n - 1, alphabet).map( (s: String) => c + s ))
  }
}
