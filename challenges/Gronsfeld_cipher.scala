import scala.collection.immutable.IndexedSeq
import scala.collection.mutable
import Array._

/**
  */

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(";") match {
      case Array(key, message) => println(decipher(key, message))
    }
  }

  def decipher(key: String, message: String): String = {

    val alphabet = " !\"#$%&'()*+,-./0123456789:<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

    val fullKey = (key * (message.length / key.length + 1)).substring(0, message.length)

    message.zip(fullKey).map((x: (Char, Char)) => {
      alphabet.indexOf(x._1) - x._2.asDigit match {
        case y if y >= 0 => alphabet.charAt(y)
        case y if y < 0  => alphabet.charAt(alphabet.length - -y)
      }
    }).mkString
  }
}


