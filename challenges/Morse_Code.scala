/*Sample code to read in test cases:*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  val morze: Map[String, String] = Map(
    ".-" -> "A", "--." -> "G", "--" -> "M", "..." -> "S", "-.--" -> "Y",
    "-..." -> "B", "...." -> "H", "-." -> "N", "-" -> "T", "--.." -> "Z",
    "-.-." -> "C", ".." -> "I", "---" -> "O", "..-" -> "U",
    "-.." -> "D", ".---" -> "J", ".--." -> "P", "...-" -> "V",
    "." -> "E", "-.-" -> "K", "--.-" -> "Q", ".--" -> "W",
    "..-." -> "F", ".-.." -> "L", ".-." -> "R", "-..-" -> "X",
    ".----" -> "1", "-...." -> "6",
    "..---" -> "2", "--..." -> "7",
    "...--" -> "3", "---.." -> "8",
    "....-" -> "4", "----." -> "9",
    "....." -> "5", "-----" -> "0"
  )

  for (line <- lines) {
    println(line.split("  ").map(word =>
      word.split(" ").toList.map(letter => morze.getOrElse(letter, ""))
    ).map(x => x.mkString).mkString(" "))
  }

}