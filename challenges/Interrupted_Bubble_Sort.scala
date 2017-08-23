import scala.collection._

import scala.io.Source

object Main extends App {

  val src = Source.fromFile(args(0))
  val lines = src.getLines().filter(_.length > 0)

  def bubleSort(array: Array[Int], iterations: Long): Array[Int] = {
    /**
     * Simple swap values in array from index a and b
     */
    def swap(array: Array[Int], a: Int, b: Int) = {
      val value = array(a)
      array(a) = array(b)
      array(b) = value
    }

    var swapped : Boolean = false // somthing has been swapped
    var counter : Long = 0     // iterations counter
    do {
      swapped = false
      for (i <- 1 until array.length) {
        if (array(i - 1) > array(i)) {
          /* swap them and remember something changed */
          swap(array, i - 1, i)
          swapped = true
        }
      }
      // increase iterations counter
      counter = counter + 1
    } while (swapped && counter != iterations)
    // return result
    array
  }

  def sorted(array: Array[Int]): Boolean = (array, array.tail).zipped.forall(_ <= _)

  for (line <- lines) {
    line.split(" \\| ") match {
      case Array(a, b) => {
        val array : Array[Int] = a.split(" ").map(_.toInt)
        if (sorted(array)) {
          println(array.mkString(" "))
        } else {
          println(bubleSort(array, b.toLong).mkString(" "))
        }
      }
    }
  }

}
