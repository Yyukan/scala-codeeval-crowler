/**
CHALLENGE DESCRIPTION:

  Print the odd numbers from 1 to 99.

INPUT SAMPLE:

  There is no input for this program.

OUTPUT SAMPLE:

  Print the odd numbers from 1 to 99, one number per line.
*/
object Main extends App {

  (1 to 99).filter(_ % 2 != 0).map(println(_))

}
