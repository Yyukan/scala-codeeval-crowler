import java.text.SimpleDateFormat

object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  val format = {
    val format = new SimpleDateFormat("HH:mm:ss")
    format.setTimeZone(java.util.TimeZone.getTimeZone("UTC"))
    format
  }

  for (line <- lines) {
    line.split(" ") match {
      case Array(a, b) => {
        val time1 = format.parse(a).getTime
        val time2 = format.parse(b).getTime

        val diff = if (time2 > time1 ) time2 - time1 else time1 - time2

        println(format.format(new java.util.Date(diff)))
      }
    }

  }

}

