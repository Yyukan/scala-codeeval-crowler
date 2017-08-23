import java.math.RoundingMode
import scala.util.parsing.combinator._

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)


  def formatResult(f: BigDecimal) = {
    val result = f.underlying().setScale(5, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString
    if (result == "0.00000") "0" else result
  }

  for (line <- lines) {
    println(formatResult(Calculator.evaluate(line)))
  }

}

sealed trait Expression
case class Number(value: BigDecimal) extends Expression
case class UnaryOp(operator: String, expression: Expression) extends Expression
case class BinaryOp(operator: String, left: Expression, right: Expression) extends Expression

object ExpressionParser extends JavaTokenParsers with PackratParsers {

  lazy val expr: PackratParser[Expression] =
    (expr <~ "+") ~ term ^^ { case left~right => BinaryOp("+", left, right) } |
    (expr <~ "-") ~ term ^^ { case left~right => BinaryOp("-", left,right) } |
     term

  lazy val term: PackratParser[Expression] =
    (term <~ "mod") ~ factor ^^ { case left~right => BinaryOp("mod", left,right) } |
    (term <~ "*") ~ factor ^^ { case left~right => BinaryOp("*", left,right) } |
    (term <~ "/") ~ factor ^^ { case left~right => BinaryOp("/", left, right) } |
     factor

  lazy val factor: PackratParser[Expression] =
    (factor <~ "^") ~ exp ^^ { case left~right => BinaryOp("^", left,right) } |
    exp

  lazy val exp: PackratParser[Expression] =
      (expr ~ "!") ^^ { case left~_ => UnaryOp("!", left) } |
      ("+" ~ expr) ^^ { case _~right => UnaryOp("+", right) } |
      ("-" ~ expr) ^^ { case _~right => UnaryOp("-", right) } |
      ("|" ~> expr <~ "|") ^^ { case x => UnaryOp("|", x) } |
      "(" ~> expr <~ ")" |
      "sqrt(" ~> expr <~ ")" ^^ { case x => UnaryOp("sqrt", x) } |
      "cos(" ~> expr <~ ")" ^^ { case x => UnaryOp("cos", x) } |
      "sin(" ~> expr <~ ")" ^^ { case x => UnaryOp("sin", x) } |
      "tan(" ~> expr <~ ")" ^^ { case x => UnaryOp("tan", x) } |
      "lg(" ~> expr <~ ")" ^^ { case x => UnaryOp("lg", x) } |
      "ln(" ~> expr <~ ")" ^^ { case x => UnaryOp("ln", x) } |
      ident ^^ {
        case "Pi" => Number(BigDecimal(MathUtil.PI))
        case "e" => Number(BigDecimal(MathUtil.E))
      } |
      floatingPointNumber ^^ {x => Number(BigDecimal(x)) }

  def parse(string : String): ParseResult[Expression] = parseAll(expr, string)
}

object MathUtil {

  val PI = Math.PI
  val E = Math.E

  def factorial(num: BigDecimal): BigDecimal = {
    (1 to num.toInt).map(x => BigDecimal.valueOf(x)).foldLeft(BigDecimal.valueOf(1)) ((a,b) => a * b)
  }
}


object Calculator {

  def evaluate(string: String):BigDecimal = {

    def aux(expr: Expression): BigDecimal = {
      expr match {
        case Number(x) => x
        case UnaryOp("-", x) => -aux(x)
        case UnaryOp("+", x) => aux(x)
        case UnaryOp("!", x) => MathUtil.factorial(aux(x))
        case UnaryOp("|", x) => aux(x).abs
        case UnaryOp("sqrt", x) => math.sqrt(aux(x).toDouble)
        case UnaryOp("cos", x) => math.cos(aux(x).toDouble)
        case UnaryOp("sin", x) => math.sin(aux(x).toDouble)
        case UnaryOp("tan", x) => math.tan(aux(x).toDouble)
        case UnaryOp("lg", x) => math.log10(aux(x).toDouble)
        case UnaryOp("ln", x) => math.log(aux(x).toDouble)
        case BinaryOp("mod", x1, x2) => aux(x1) % aux(x2)
        case BinaryOp("+", x1, x2) => aux(x1) + aux(x2)
        case BinaryOp("-", x1, x2) => aux(x1) - aux(x2)
        case BinaryOp("*", x1, x2) => aux(x1) * aux(x2)
        case BinaryOp("/", x1, x2) => aux(x1) / aux(x2)
        case BinaryOp("^", x1, x2) => aux(x1).pow(aux(x2).toInt)
        case _ => println("Error in operation"); 0.0f
      }
    }

    aux(ExpressionParser.parse(string).get)
  }
}

