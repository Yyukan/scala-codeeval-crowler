object Main extends App {
//  val source = scala.io.Source.fromFile(args(0))
//  val lines = source.getLines.filter(_.length > 0).toList
//
//  for (line <- lines) {
//
//  }

  val key = "BHISOECRTMGWYVALUZDNFJKPQX"
  val message = "012222 1114142503 0313012513 03141418192102 0113 2419182119021713 06131715070119"

  println(decrypt(message, key))

  def decrypt(message: String, key: String): String = {
    val alphabet = ('A' to 'Z').mkString

    // replace one space to be able to slide by 2 elements
    val decrypted = message.replaceAll(" ", "  ").sliding(2, 2).map({
      // map every two spaces to one
      case "  " => " "
      // map every tho digits to alphabet symbol and then decrypt via key
      case twoDigits => alphabet(key.indexOf(alphabet(twoDigits.toInt)))
    })

    decrypted.mkString
  }

}