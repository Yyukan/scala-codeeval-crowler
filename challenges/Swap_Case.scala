/**
CHALLENGE DESCRIPTION:

  Write a program which swaps letters' case in a sentence. All non-letter characters should remain the same.

INPUT SAMPLE:

  Your program should accept as its first argument a path to a filename. Input example is the following

Hello world!
  JavaScript language 1.8
A letter
  OUTPUT SAMPLE:

  Print results in the following way.

hELLO WORLD!
  jAVAsCRIPT LANGUAGE 1.8
a LETTER
*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(swapCase(line))
  }

  def swapCase(line: String):String = {
    line map {
      case u if u.isUpper => u.toLower
      case l if l.isLower => l.toUpper
      case x => x
    }
  }

}
