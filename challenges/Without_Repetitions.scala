/**
CHALLENGE DESCRIPTION:

In a given text, if there are two or more identical characters in sequence, delete the repetitions and leave only the first character.

For example:

Shellless mollusk lives in wallless house in wellness. Aaaarrghh!

↓

Sheles molusk lives in wales house in welnes. Aargh!
  */
object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  var lastSymbol: Char = '.'

  for (line <- lines) {

    println(line.filter(c => {
      if (c.equals(lastSymbol)) {
        false
      } else {
        lastSymbol = c
        true
      }
    }))
  }
}
