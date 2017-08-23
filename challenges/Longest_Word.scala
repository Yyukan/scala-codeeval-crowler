/**
CHALLENGE DESCRIPTION:

  In this challenge you need to find the longest word in a sentence. If the sentence has more than one word of the same
  length you should pick the first one.

INPUT SAMPLE:

  Your program should accept as its first argument a path to a filename. Input example is the following

some line with text
another line
  Each line has one or more words. Each word is separated by space char.

  OUTPUT SAMPLE:

  Print the longest word in the following way.

some
another
*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)


  def longestWord(line: String): String = {
    line.split(' ').foldLeft("") { (result, word) =>
      word match {
        case x if word.length > result.length => x
        case _ => result
      }
    }
  }

  for (line <- lines) {
    println(longestWord(line))
  }
}
