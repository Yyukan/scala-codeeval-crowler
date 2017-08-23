import scala.collection.immutable.IndexedSeq
import scala.collection.mutable
import Array._

/**
  */

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split(" ").map(_.toInt) match {
      case Array(o, p, q, r) => {
        println(direction(new Point(o, p), new Point(q, r)))
      }
    }
  }

  def direction(p1: Point, p2: Point) : String = {
    p1 - p2 match {
      case Point(0, 0) => "here"
      case Point(0, z) if z > 0 => "S"
      case Point(0, z) if z < 0 => "N"
      case Point(z, 0) if z > 0 => "W"
      case Point(z, 0) if z < 0 => "E"
      case Point(x, y) if x < 0 && y < 0 => "NE"
      case Point(x, y) if x > 0 && y > 0 => "SW"
      case Point(x, y) if x > 0 && y < 0 => "NW"
      case Point(x, y) if x < 0 && y > 0 => "SE"
      case _ => "Wrong"
    }

  }

  case class Point(x: Int, y: Int) {

    def -(other : Point): Point = {
      new Point(x - other.x, y - other.y)
    }
  }
}


