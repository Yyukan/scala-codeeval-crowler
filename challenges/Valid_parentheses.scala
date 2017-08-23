import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    isWellFormat(line) match {
      case true =>  println("True")
      case false => println("False")
    }
  }

  def isWellFormat(line:String):Boolean = {
    val stack = new mutable.Stack[Char]()

    line.foreach {
      case '(' => stack.push('(')
      case '{' => stack.push('{')
      case '[' => stack.push('[')
      case ')' => if (stack.isEmpty || stack.pop() != '(') return false
      case ']' => if (stack.isEmpty || stack.pop() != '[') return false
      case '}' => if (stack.isEmpty || stack.pop() != '{') return false
    }

    if (stack.isEmpty) true else false
  }

}
