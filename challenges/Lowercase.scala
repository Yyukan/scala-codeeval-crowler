/**
CHALLENGE DESCRIPTION:

  Given a string write a program to convert it into lowercase.

  INPUT SAMPLE:

The first argument will be a path to a filename containing sentences, one per line. You can assume all characters are from the english language. E.g.

  HELLO CODEEVAL
  This is some text
  OUTPUT SAMPLE:

  Print to stdout, the lowercase version of the sentence, each on a new line. E.g.

hello codeeval
  this is some text
  */
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(line.map(_.toLower))
  }

}
