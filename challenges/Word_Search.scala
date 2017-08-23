import scala.collection.mutable

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)


  for (line <- lines) {
    println(Board.exists(line).toString.capitalize)
  }

  /**
  [
  [ABCE],      0 1  2  3
  [SFCS],      4 5  6  7
  [ADEE]       8 9 10 11
  ]
  */

  object Board {

    val vertexes: List[Char] = List('A', 'B', 'C', 'E', 'S', 'F', 'C', 'S', 'A', 'D', 'E', 'E')
    val zipped = vertexes.zipWithIndex

    val links: Map[Int, Set[Int]] = Map(
      0 -> Set(1, 4),
      1 -> Set(0, 2, 5),
      2 -> Set(1, 3, 6),
      3 -> Set(2, 7),
      4 -> Set(0, 5, 8),
      5 -> Set(1, 4, 6, 9),
      6 -> Set(2, 5, 7, 10),
      7 -> Set(3, 6, 11),
      8 -> Set(4, 9),
      9 -> Set(5, 8, 10),
      10 -> Set(6, 9, 11),
      11 -> Set(7, 10)
    )

    // returns list of vertexes for specified char
    def start(char: Char):List[Int] = {
      zipped.filter( p => p._1 == char).map(_._2)
    }

    // returns alignment vertexes where char is existed
    def next(vertex: Int, char:Char):List[Int] = {
      val adjacent = links(vertex)
      zipped.filter(p => adjacent.contains(p._2) && p._1 == char).map(_._2)
    }

    // check if word exists from the vertex
    def exist(vertex: Int, word: String, visited:Set[Int]):Boolean = {
      word.toList match {
        case Nil => true
        case head :: tail =>
          val adjacent = next(vertex, head)

          adjacent foreach { x =>
            if (!visited.contains(x) && exist(x, tail.mkString, visited + vertex)) return true
          }

          false
      }
    }

    def exists(line: String):Boolean = {

      def aux(vertexes: List[Int], word: String):Boolean = {
        vertexes match {
          // if no vertexes for the first letter return false
          case Nil => false

          // check board for every vertex
          case head :: tail =>
            Board.exist(head, word, Set()) match {
              case false => aux(tail, word)
              case true => true
            }
        }
      }

      // get all vertexes for the very first letter
      aux(start(line.head), line.tail)
    }
  }
}

