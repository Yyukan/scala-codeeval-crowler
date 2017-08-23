import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  def ipToInt(ip: String) : Array[Int] = ip.split("\\.").map(_.toInt)

  def ipToHex(ip: Array[Int]) : Array[String] = ip.map(x => Integer.toHexString(0x100 | x).substring(1))

  for (line <- lines) {
    val data: Array[String] = line.split(" ")
    val source = data(0)
    val destination = data(1)
    val packet = data.drop(2)
    val header = packet.take(20)

    //println(header.mkString(" "))

    val newHeader = header.take(12) ++ ipToHex(ipToInt(source)) ++ ipToHex(ipToInt(destination))
    val newHeaderCheckSum = checkSum(newHeader)
    val result = header.take(10) ++ newHeaderCheckSum ++ ipToHex(ipToInt(source)) ++ ipToHex(ipToInt(destination))
    println(result.mkString(" "))
  }

  /**
   * Calculates checksum of the header
   *
   * @param header 20 bytes header
   * @return checksum in form "xx xx"
   */
  def checkSum(header: Array[String]) : Array[String] = {
    // skipping only the checksum field
    val withoutCheckSum = header.take(10) ++ header.takeRight(8)

    //first calculate the sum of each 16 bit value within the header
    val sum = withoutCheckSum.sliding(2, 2).map(_.mkString).map(Integer.parseInt(_, 16)).sum
    // the first 4 bits are the carry and will be added to the rest of the value
    val rest = Integer.toBinaryString(sum).takeRight(16)
    val carry = Integer.toBinaryString(sum).dropRight(16)
    var tmp = Integer.toBinaryString(Integer.parseInt(rest, 2) + Integer.parseInt(carry, 2))
    // added leading zerous
    if (tmp.length < 16) {
      tmp = "0" * (16 - tmp.length) + tmp
    }
    // next, we flip every bit in that value, to obtain the checksum:
    tmp = tmp.map({
      case '0' => '1'
      case '1' => '0'
    })

    val result: String = Integer.toHexString(0x10000 | Integer.parseInt(tmp, 2)).substring(1)
    Array(result.take(2),result.takeRight(2))
  }
}


