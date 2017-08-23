/**
CHALLENGE DESCRIPTION:

  You have a string of words and digits divided by comma. Write a program which separates words with digits. You shouldn't change the order elements.

  INPUT SAMPLE:

  Your program should accept as its first argument a path to a filename. Input example is the following

8,33,21,0,16,50,37,0,melon,7,apricot,peach,pineapple,17,21
24,13,14,43,41
OUTPUT SAMPLE:

  melon,apricot,peach,pineapple|8,33,21,0,16,50,37,0,7,17,21
24,13,14,43,41
As you cas see you need to output the same input string if it has words only or digits only.
 */
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(mixedContent(line))
  }

  def mixedContent(line: String):String = {
    val content = line.split(',').groupBy(x =>
      if (isDigit(x)) "digits" else "words").mapValues(x => x.mkString(","))

    content.size match {
      case 2 => content("words") + "|" + content("digits")
      case 1 => content.getOrElse("words", content("digits"))
      case _ => ""
    }
  }

  def isDigit(x: String) = x forall Character.isDigit
}
