import scala.collection.mutable

/**
 *FILENAME PATTERN
CHALLENGE DESCRIPTION:

You are given a filename pattern and a list of filenames. Find out which filenames from the list are matching the given pattern. The pattern can contain characters and the following wildcards:

? — matches any single character.
 * — matches everything (any multiple characters, any single character, or empty string).
[abc] — matches any character inside brackets (in this example: a, b, or c).
Matching is case-sensitive.

*/

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val input = line.split(" ")
    val regexp = "^"+ input.head.replaceAll("\\.", "\\\\.").replaceAll("\\*", ".{0,}").replaceAll("\\?", ".{1}") + "$"
    val files = input.tail

    //println(regexp)
    files.filter(file => {
      regexp.r.findFirstIn(file).isDefined
    }).toList match {
      case x :: xs => println((x :: xs).mkString(" "))
      case Nil => println("-")
    }
  }


}


