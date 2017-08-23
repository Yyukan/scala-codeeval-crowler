import scala.collection.immutable.IndexedSeq
import scala.collection.mutable
import Array._

/**
MATRIX ROTATION

CHALLENGE DESCRIPTION:

You are given a 2D N×N matrix. Each element of the matrix is a letter: from ‘a’ to ‘z’.
Your task is to rotate the matrix 90° clockwise:

  */

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val matrix = Matrix(line.split(" ").map(_.charAt(0)))
    val rotated = matrix.clockwise()
    //rotated.draw()
    println(rotated)
  }

  case class Matrix(dimension: Int, data: Array[Array[Char]]) {

    def clockwise(): Matrix = {
      val x: IndexedSeq[Char] = for {
        j <- 0 until dimension
        i <- dimension - 1 to 0 by -1
      } yield data(i)(j)

      Matrix(x.toArray)
    }

    def draw() = {
      for (i <- 0 until dimension) {
        for (j <- 0 until dimension) {
          print(" " + data(i)(j))
        }
        println()
      }
    }

    override def toString = {
      data.flatMap(_.mkString).mkString(" ")
    }

  }

  case object Matrix {

    val size: PartialFunction[Int, Int] = {
      case 100 => 10
      case 81 => 9
      case 64 => 8
      case 49 => 7
      case 36 => 6
      case 25 => 5
      case 16 => 4
      case 9 => 3
      case 4 => 2
      case 1 => 1
    }

    def apply(data: Array[Char]): Matrix = {
      val dimension = size(data.size)
      val array = ofDim[Char](dimension, dimension)

      for {
        i <- 0 until dimension
        j <- 0 until dimension
      } array(i)(j) = data(i * dimension + j)

      new Matrix(dimension, array)
    }
  }

}


