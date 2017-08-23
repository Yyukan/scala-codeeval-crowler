


object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  for (line <- lines)
  {
    val angle = BigDecimal(line)
    val degree: (BigDecimal, BigDecimal) = angle /% BigDecimal(1)
    val minutes = (degree._2 * 60) /% BigDecimal(1)
    val seconds = (minutes._2 * 60) /% BigDecimal(1)

    println("%d.%02d'%02d\"".format(degree._1.toInt, minutes._1.toInt, seconds._1.toInt))
  }
}

