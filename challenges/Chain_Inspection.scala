object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val data = line.split(";").map(s => s.split("-") match {
      case Array(a, b) => (a, b)
    }).toMap

    inspect(data)
  }

  def inspect(chain: Map[String, String]):Unit = {

    def aux(chain:Map[String, String], head: String):String = {
      head match {
        case "END" if chain.isEmpty => "GOOD"
        case "END" => "BAD"
        case _ if chain.isEmpty => "BAD"
        case _ =>  chain.get(head) match {
          case None => "BAD"
          case Some(value) => aux(chain - head, value)
        }
      }

    }

    val head = chain("BEGIN")
    println(aux(chain - "BEGIN", head))
  }
}
