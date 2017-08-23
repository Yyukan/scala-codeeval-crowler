import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {

    val counts = line.foldLeft[Map[Char, Int]](Map.empty)( (acc, c:Char) =>
      acc + (c -> (acc.getOrElse(c, 0) + 1))
    )
    println(line.filter(c => counts(c) == 1).head)
  }

}
