

object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  val coder:Map[Char, Char] = Map(
    'a' -> 'y',
    'b' -> 'h',
    'c' -> 'e',
    'd' -> 's',
    'e' -> 'o',
    'f' -> 'c',
    'g' -> 'v',
    'h' -> 'x',
    'i' -> 'd',
    'j' -> 'u',
    'k' -> 'i',
    'l' -> 'g',
    'm' -> 'l',
    'n' -> 'b',
    'o' -> 'k',
    'p' -> 'r',
    'q' -> 'z',
    'r' -> 't',
    's' -> 'n',
    't' -> 'w',
    'u' -> 'j',
    'v' -> 'p',
    'w' -> 'f',
    'x' -> 'm',
    'y' -> 'a',
    'z' -> 'q',
    ' ' -> ' ')

  for (line <- lines) {
    println(line.map(c => coder.getOrElse(c, c)))
  }
}