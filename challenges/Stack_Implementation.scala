import scala.collection.mutable.ListBuffer

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    val numbers = line.split(" ").map(_.toInt)

    val stack = new StackImpl
    numbers.foreach { x =>
      stack.push(x)
    }

    while(!stack.isEmpty) {
      stack.pop() match {
        case Some(value) => print(value + " ")
        case None =>
      }
    }
    println
  }

  trait Stack {
    def push(element: Int)

    /**
     * Pop every alternate integer
     */
    def pop():Option[Int]

    def isEmpty:Boolean
  }

  class StackImpl extends Stack {
    val stack = ListBuffer[Int]()

    def push(element: Int):Unit = {
      stack.append(element)
    }

    def pop():Option[Int] = {
      if (isEmpty) return None

      val result = Some(stack.remove(stack.length - 1))
      if (!isEmpty) stack.remove(stack.length - 1)
      result
    }

    def isEmpty:Boolean = {
      stack.isEmpty
    }
  }

}
