/**
CHALLENGE DESCRIPTION:

  Write a program which finds the next-to-last word in a string.

  INPUT SAMPLE:

Your program should accept as its first argument a path to a filename. Input example is the following

some line with text
another line
  Each line has more than one word.

  OUTPUT SAMPLE:

  Print the next-to-last word in the following way.

with
another
*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  val pattern = """(\w+)\s+\w+$""".r
  for (line <- lines) {
    pattern.findAllIn(line).matchData foreach {
      m => println(m.group(1))
    }
  }
}
