import scala.collection.mutable
import scala.util.control.Breaks._

/**
 * STRING SEARCHING
CHALLENGE DESCRIPTION:

You are given two strings.
Determine if the second string is a substring of the first (Do NOT use any substr type library function).
The second string may contain an asterisk(*) which should be treated as a regular expression
i.e. matches zero or more characters.
The asterisk can be escaped by a \ char in which case it should be interpreted as a regular '*' character.
To summarize: the strings can contain alphabets, numbers, * and \ characters.

INPUT SAMPLE:

Your program should accept as its first argument a path to a filename.
The input file contains two comma delimited strings per line. E.g.

Hello,ell
This is good, is
CodeEval,C*Eval
Old,Young
 */

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(",") match {
      case Array(string, pattern) => println(isSubstring(string, pattern))
    }
  }

  def isContain(string: String, char: Char): Option[Int] = {
    for (i <- 0 until string.length) {
      if (string(i) == char)
        return Some(i)
    }
    None
  }

  def isSubstring(string: String, pattern: String):Boolean = {

    def aux(string: String, pattern: String):Boolean = {
      val iterator1 = string.iterator
      val iterator2 = pattern.iterator

      while (iterator2.hasNext) {
        if (!iterator1.hasNext) return false
        var a: Char = iterator1.next()

        var b: Char = iterator2.next()

        if (b == '\\') {
          // expect astericks here '*'
          b = iterator2.next()
        } else if (b == '*') {
          // next symbol
          if (!iterator2.hasNext) return true
          b = iterator2.next()
          // skip symbols from string until b
          breakable {
            while(iterator1.hasNext) {
              a = iterator1.next()
              if (a == b) break
            }
          }
        }

        if (a != b) return false
      }

      true
    }

    if (string.length == 0) return false

    if (pattern == "*") return true

    if (pattern.head == '*') return isSubstring(string, pattern.tail)

    isContain(string, pattern.head) match {
      case Some(index) => aux(string.drop(index), pattern) match {
        case false => isSubstring(string.drop(index + 1), pattern)
        case true => true
      }
      case None => false
    }
  }

}
