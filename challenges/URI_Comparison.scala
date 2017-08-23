object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    //println(line)
    line.split(";") match {
      case Array(a, b) => {
        val url1 = UrlSchema(a)
        val url2 = UrlSchema(b)

        url1 equals url2 match {
          case true => println("True")
          case false => println("False")
        }
      }
    }
  }

  class UrlSchema(val schema:String, val host: String, val port: String = "80" , val path: String) {

    override def equals(other: Any):Boolean = {
      //println(this)
      //println(other)

      val that: UrlSchema = other.asInstanceOf[UrlSchema]
      if (!schema.equalsIgnoreCase(that.schema)) {
        return false
      }

      if (!host.equalsIgnoreCase(that.host)) {
        return false
      }

      if (!port.equals(that.port)) {
        return false
      }

      if (!path.equals(that.path)) {
        return false
      }

      true
    }

    override def toString:String = {
      "UrlSchema(" + schema + host + port + path + ")"
    }
  }

  object UrlSchema {

    def valueToChar(hex: String):Char = Integer.parseInt(hex, 16).toChar

    def resolveHex(string: String):String = {
      val hexLetter = "abcdef"
      val result = new StringBuilder()

      var number = false
      var digits = ""
      string.foreach {
        case '%' =>
          number = true
        case x if number && (x.isDigit || hexLetter.contains(x.toLower)) =>
            digits += x
            if (digits.length == 2) {
              result.append(valueToChar(digits))
              digits = ""; number = false
            }
        case x =>
          if (number) {
            number = false
            digits = ""
            result.append("%" + x)
          } else {
            result.append(x)
          }
      }

      result.toString()
    }

    def apply(url: String):UrlSchema = {

      val schema = url.substring(0, url.indexOf("://"))
      val rest = url.stripPrefix(schema + "://")

      var hostPort = ""
      if (rest.contains("/")) {
        hostPort = rest.substring(0, rest.indexOf('/'))
      } else {
        hostPort = rest
      }

      val (host, port) = if(hostPort.contains(":")) hostPort.splitAt(hostPort.indexOf(":")) else (hostPort, ":80")

      val path = rest.stripPrefix(hostPort)

      new UrlSchema(schema, host, port.tail, resolveHex(path))
    }
  }

}
