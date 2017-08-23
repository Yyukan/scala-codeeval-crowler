object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  val font = "-**----*--***--***---*---****--**--****--**---**--" +
             "*--*--**-----*----*-*--*-*----*-------*-*--*-*--*-" +
             "*--*---*---**---**--****-***--***----*---**---***-" +
             "*--*---*--*-------*----*----*-*--*--*---*--*----*-" +
             "-**---***-****-***-----*-***---**---*----**---**--" +
             "--------------------------------------------------"

  for (line <- lines) {
    val filtered = line.flatMap {
      case x if x.isDigit =>  Some(x)
      case _ => None
    }

    val result:StringBuilder = new StringBuilder

    (0 until 6).foreach( i => {
      filtered.foreach( (x: Char) => result.append(alphabet(x.asDigit, i)))
      result.append("\n")
    })

    print(result.toString())
  }

  def alphabet(x: Int, line: Int) : String = {
     val offset = 5 * x + line * 50
     font.substring(offset, offset + 5)
  }
}

