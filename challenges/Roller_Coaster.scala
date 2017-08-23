import scala.io.Source

object Main extends App {

  val src = Source.fromFile(args(0))
  val lines = src.getLines().filter(_.length > 0)
  for (line <- lines) {
    println(capitalize(line))
  }

  def capitalize(line : String) : String = {
    var capitalize: Boolean = true

    line.map((c : Char) => {
      if (c.isLetter) {
        if (capitalize) {
          capitalize = false;
          c.toUpper
        } else {
          capitalize = true;
          c
        }
      } else c
    })
  }

}
