
object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(if (isHappyNumber(line.toInt)) '1' else '0')
  }

  def sumDigits(n:Int):Int = n.toString.foldLeft(0)((acc, c: Char) => acc + c.asDigit * c.asDigit)

  def isHappyNumber(n:Int, limit:Int, tries:Int, sums:Set[Int]):Boolean = {
    sumDigits(n) match {
      case 1 => true
      case x => !sums.contains(x) && tries+1 <= limit && isHappyNumber(x, limit, tries+1, sums + x)
    } 
  }

  def isHappyNumber(n:Int):Boolean = isHappyNumber(n, 100, 0, Set[Int]())
}