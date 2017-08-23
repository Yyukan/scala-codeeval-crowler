import scala.collection.mutable
import scala.math.BigDecimal.RoundingMode

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(evaluate(line).setScale(0, RoundingMode.HALF_UP).underlying().stripTrailingZeros().toPlainString)
  }

  def evaluate(line: String): BigDecimal = {
    var operands = new mutable.Stack[BigDecimal]()
    val operators = new mutable.Stack[Char]()

    line.split(" ").foreach {
      case "+" => operators.push('+')
      case "*" => operators.push('*')
      case "/" => operators.push('/')
      case "-" => operators.push('-')
      case x => operands.push(x.toInt)
    }

    operands = operands.reverse

    while(!operators.isEmpty) {
      val operand1 = operands.pop()
      val operand2 = operands.pop()

      operators.pop() match {
        case '+' => operands.push(operand1 + operand2)
        case '*' => operands.push(operand1 * operand2)
        case '/' => operands.push(operand1 / operand2)
        case '-' => operands.push(operand1 - operand2)
      }
    }

    operands.pop()
  }
}
