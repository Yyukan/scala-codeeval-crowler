
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  case class Point(x: Int, y: Int)

  for (line <- lines) {
    line.replaceAll("\\(", "").replaceAll("\\)","").replaceAll(" ", "").split(",") match {
      case Array(a,b,c,d,e,f,g,h) => {
        val p1 = new Point(a.toInt, b.toInt)
        val p2 = new Point(c.toInt, d.toInt)
        val p3 = new Point(e.toInt, f.toInt)
        val p4 = new Point(g.toInt, h.toInt)

        println(isSquare(p1, p2, p3, p4))
      }
    }
  }

  def isSquare(a: Point, b: Point, c:Point, d: Point):Boolean = {

    def distance(p1: Point, p2:Point):Int = {
     (p2.x - p1.x)*(p2.x - p1.x) + (p2.y - p1.y)*(p2.y - p1.y)
    }

    val ab = distance(a, b)
    val ac = distance(a, c)
    val ad = distance(a, d)
    val bd = distance(b, d)
    val bc = distance(b, c)
    val cd = distance(c, d)

    Set(ab, ac, ad, bd, bc, cd).size == 2
  }

}
