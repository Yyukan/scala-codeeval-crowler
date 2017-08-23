/*Sample code to read in test cases:*/
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
 for (line <- lines) {
    println(multiply(line))
  }

  def multiply(line:String):String = {
    def asNumbers(list1: String): Array[Int] = list1.trim.split(" ").map(_.toInt)
    
    line.split("\\|") match {
      case Array(list1, list2) => {
        (asNumbers(list1) zip asNumbers(list2)).map(x => x._1 * x._2).mkString(" ")
      }
    }
  }
  


}