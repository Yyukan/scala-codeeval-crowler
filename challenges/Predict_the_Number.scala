/**
 * CHALLENGE DESCRIPTION:

Sequence 011212201220200112 ... constructed as follows: first is 0, then repeated the following action:
already written part is attributed to the right with replacement 0 to 1, 1 to 2, 2 to 0. E.g.

0 -> 01 -> 0112 -> 01121220 -> ...
Create an algorithm which determines what number is on the N-th position in the sequence.

INPUT SAMPLE:

Your program should accept as its first argument a path to a filename. Each line in this file contains an integer N such as
0 <= N <= 3000000000. E.g.

0
5
101
25684
OUTPUT SAMPLE:

Print out the number which is on the N-th position in the sequence. E.g.

0
2
1
0
*/

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(line.toLong.toBinaryString.count( _ == '1') % 3)
  }

}
