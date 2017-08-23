import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex
import scala.util.matching.Regex.Match

/**
THE MINISTRY OF TRUTH
CHALLENGE DESCRIPTION:

It's 1984, and you are working as an official at the Ministry of Truth. You have intersected a message subjected to Big
Brother's doctrine.

Your task is to delete letters from certain "utterances" in order to replace it with an approved "utterance" by the Ministry.
A "word" is defined as a single sequence of Latin letters and an "utterance" is a sequence of multiple words and spaces.

To compare two "utterances," you must first replace all blocks of spaces with one space.
"Utterances" are considered identical when resulting expressions match.
One line contains two different expressions separated by the symbol ';'.
The first expression is the original utterance and second one is the utterance you want to get.
If you cannot fulfill the order, print a single line «I cannot fix history». Otherwise, output the original utterance by replacing the letters that must be erased with underscore character and by replacing all blocks of white spaces by a single white space.

INPUT SAMPLE:

Your program should accept as its first argument a path to a filename. E.g.:

Higher meaning;Hi mean
this is impossible;im possible
twenty   two minutes;two minutes
Higher meaning;e

  */

object Main extends App {

  import scala.util.control.Breaks._

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  for (line <- lines) {
    try {
      line.split(";") match {
        case Array(a, b) => println(utterance(a.replaceAll("\\s+", " "), b.replaceAll("\\s", " ")))
        case Array(a) => println(a.replaceAll("\\s+", " ").split(" ").map(x => x.map(x => '_')).mkString(" "))
      }
    } catch {
      case e: java.lang.Throwable => println(line); println(e)
    }
  }


  def utterance(original: String, desired: String): String = {

    def substitute(word: String, utterance: String): String = {
      val index = word.indexOf(utterance)
      "_" * index + utterance + ("_" * (word.length - index - utterance.length))
    }

    def replace(originals: List[String], desires: List[String]): List[String] = {

      var index = 0

      def mapper(word: String): String = {
        for (i <- index until desires.length) {
          if (word contains desires(i)) {
            index = i + 1
            return substitute(word, desires(i))
          } else {
            return "_" * word.length
          }
        }
        "_" * word.length
      }


      val result = originals.map(mapper)
      if (result.mkString.replaceAll("_", "").equals(desires.mkString)) {
        result
      } else {
        Nil
      }
    }


    replace(original.split(" ").toList, desired.split(" ").toList) match {
      case Nil => "I cannot fix history"
      case x => x.mkString(" ")
    }


  }


}

