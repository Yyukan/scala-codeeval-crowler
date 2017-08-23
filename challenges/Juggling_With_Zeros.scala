object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    // create list of pair (flag, sequence)
    val pairs: Iterator[List[String]] = line.split(" ").toList.sliding(2, 2)
    val binary: String = pairs.foldLeft("")((accumulator, flagAndSequence) => {
      flagAndSequence match {
        case List(flag, sequence) => flag match {
          case "0" => accumulator + sequence // sequence of zeros should be appended to a binary string
          case "00" => accumulator + sequence.map(_ => '1') // sequence of zeroes should be transformed into a sequence of ones and appended
        }
        case _ => println("Error !"); ""
      }
    })

    println(java.lang.Long.parseLong(binary, 2))
  }

}
