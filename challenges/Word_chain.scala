import scala.collection._

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
      //println(line)
    val words = line.split(',').toSet
    chain(words)
  }

  def chain(words: Set[String]):Unit = {

    var maximum = 0

    def aux(acc: List[String], word: String, tail:Set[String]):Unit = {

       for (element <- tail) {
         if (element.head == word.last) {
           aux(element :: acc, element, tail - element)
         }
       }

       maximum = maximum.max(acc.size)
    }

    for (word <- words) {
      aux(List(word), word, words - word)
    }

    maximum match {
      case 1 => println("None")
      case x => println(x)
    }
  }
}

