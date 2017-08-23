object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    // create map with frequency of characters
    val mostCommon:Map[Char, Int] = line.toLowerCase.foldLeft[Map[Char, Int]](Map.empty)((map, c) =>
      map + (c -> (map.getOrElse(c, 0) + 1)) ).filterKeys(_.isLetter)

    // sort by frequency descending
    val sorted = mostCommon.toList.sortBy(_._2)(Ordering[Int].reverse)

    // assign beauty to most often characters
    var x = 27
    val a = sorted.map(c => { x-=1; (c._1, x)})

    println(beauty(line, a.toMap))
  }

  def beauty(line: String, letters:Map[Char, Int]):Int = {
    line.toLowerCase.filter(_.isLetter).foldLeft(0)((acc, c:Char) => acc + letters.getOrElse(c, 0))
  }
}