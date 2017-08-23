import scala.collection.mutable

/**
 * CHALLENGE DESCRIPTION:

Long serious texts are boring. Write a program that will make texts more informal: replace every other end punctuation
mark (period ‘.’, exclamation mark ‘!’, or question mark ‘?’) with one of the following slang phrases,
selecting them one after another:

‘, yeah!’
‘, this is crazy, I tell ya.’
‘, can U believe this?’
‘, eh?’
‘, aw yea.’
‘, yo.’
‘? No way!’
‘. Awesome!’
The result should be funny.

 */

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  val slang = List(", yeah!", ", this is crazy, I tell ya.", ", can U believe this?", ", eh?", ", aw yea.", ", yo.", "? No way!", ". Awesome!")
  var replace = false
  var index = -1

  println(lines.mkString("\n").flatMap(informal))

  def informal(c: Char): List[Char] = c match {
    case '.'| '!' | '?'  =>
      if (replace) {
        replace = false
        index = index + 1
        if (index == slang.length) index = 0
        slang(index).toList
      } else {
        replace = true
        List(c)
      }
    case _ => List(c)
  }
}


