object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  val matrix: Array[Array[Short]] = Array.fill(256, 256)(0.toShort)

  for (line <- lines) {
    matrixSum(line)
  }

  def setCol(col: Int, value: Short):Unit = {
    (0 to 255).foreach { i =>
      matrix(i)(col) = value
    }
  }

  def setRow(row: Int, value: Short):Unit = {
    (0 to 255).foreach { i =>
      matrix(row)(i) = value
    }
  }

  def queryCol(col: Int):Int = {
    var sum:Int = 0
    (0 to 255).foreach { i =>
      sum += matrix(i)(col)
    }
    sum
  }

  def queryRow(row: Int):Int = {
    var sum:Int = 0
    (0 to 255).foreach { i =>
      sum += matrix(row)(i)
    }
    sum
  }

  def matrixSum(line: String) = {
    line.split(" ") match {
      case Array(set, x, y) =>
        set match {
          case "SetCol" => setCol(x.toInt, y.toShort)
          case "SetRow" => setRow(x.toInt, y.toShort)
        }

      case Array(query, x) =>
        query match {
          case "QueryCol" => println(queryCol(x.toInt))
          case "QueryRow" => println(queryRow(x.toInt))
        }
    }

  }

}