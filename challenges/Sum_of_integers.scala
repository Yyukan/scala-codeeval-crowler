

object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val numbers:List[Int] = line.split(",").map(x => x.trim.toInt).toList

    println(seq(numbers))
  }

  def seq(numbers: List[Int]):Int = {
    var max_so_far  = numbers(0)
    var max_ending_here = numbers(0)

    for(i <- 1 to numbers.size - 1) {
      if(max_ending_here < 0)
      {
        max_ending_here = numbers(i)
      }
      else
      {
        max_ending_here += numbers(i)
      }

      max_so_far = max_so_far max max_ending_here
    }
    max_so_far
  }
}