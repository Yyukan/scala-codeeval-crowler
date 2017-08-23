object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(decodings(line))
  }

  def decodings(text : String):Int = {
    if (text.isEmpty) return 1

    var count = 0
    var headSize = 1

    while(headSize <= 2 && headSize <= text.length()) {
      val head = text.substring(0, headSize)
      val tail = text.substring(headSize)

      if (head.toInt > 26) {
        return count
      }
      count += decodings(tail)

      headSize +=1
    }

    count
  }

}