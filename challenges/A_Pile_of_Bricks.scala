object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    line.split("\\|") match {
      case Array(a, b) =>
      val hole = Hole(a)
      val bricks:List[Brick] = Bricks(b)
      val result = bricks.map(brick => if (brick.fit(hole)) brick.index else 0)

      result.filter(_ != 0) match {
        case Nil => println("-")
        case list => println(list.sorted.mkString(","))
      }
    }
  }

}

case class Hole(x1: Int, y1: Int, x2: Int, y2: Int) {
  val width = (x2 - x1).abs
  val height = (y2 - y1).abs
}

object Hole {

  def xy(string:String):(Int, Int) = {
    val list = string.drop(1).dropRight(1).split(",").map(_.toInt).toList
    (list(0), list(1))
  }

  def apply(string: String):Hole = {
    string.split(" ") match {
      case Array(a, b) =>
        val (x1, y1) = xy(a)
        val (x2, y2) = xy(b)
        Hole(x1, y1, x2, y2)
    }
  }
}

case class Brick(index: Int, x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int) {
  val width = (x2 - x1).abs
  val height = (y2 - y1).abs
  val length = (z2 - z1).abs

  def fit(hole: Hole):Boolean = {

    def aux(hole: Hole, x: Int, y: Int): Boolean = {
      (x <= hole.width && y <= hole.height) || (y <= hole.width && x <= hole.height)
    }

    aux(hole, width, height) || aux(hole, width, length) || aux(hole, length, height)
  }
}

object Brick {

  def xyz(string:String):(Int, Int, Int) = {
    val list = string.drop(1).dropRight(1).split(",").map(_.toInt).toList
    (list(0), list(1), list(2))
  }

  def apply(string: String):Brick = {
    string.drop(1).dropRight(1).split(" ") match {
      case Array(a, b, c) =>
        val index = a.toInt
        val (x1, y1, z1) = xyz(b)
        val (x2, y2, z2) = xyz(c)
        Brick(index, x1, y1, z1, x2, y2, z2)
    }
  }
}

object Bricks {
  def apply(string: String):List[Brick] = {
    string.split(";").map(x => Brick(x)).toList
  }
}


