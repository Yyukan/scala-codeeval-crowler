/*Sample code to read in test cases:*/
object Main extends App {
  for (i <- 1 to 12) {
      println((1 to 12).map(x =>
        "%4d".format(x * i)
      ).mkString.trim)
    }
}
