/**
 * Locks
There are N unlocked rooms located in a row along the corridor.

A security guard, who starts the beat at the beginning of the corridor, passes by and closes the lock of every second door
(2nd, 4th, 6th…). Then he returns to the beginning of the corridor, and passes by again changing the state of every third door
(3rd, 6th, 9th…) to the opposite state — if the door is closed, security guard opens it; if the door is open, he closes it.

One iteration consists of two beats (when the security guard closes each second door, and changes the state of each third door
to the opposite state). The iteration repeats M-1 times.

During the last iteration, the security guard just changes the state of the last door in a row (closes it, if the door is open
or opens it, if the door is closed).

Your task is to determine how many doors have been left unlocked.


INPUT SAMPLE:

Your program accepts a filename as its first argument.

Each line of input contains 2 integers separated by space. The first integer represents number of doors (N),
the second — number of iterations (M).

3 1
100 100
OUTPUT SAMPLE
 */


object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  val OPEN = 1
  val CLOSE = 0

  for (line <- lines)
  {
    line.split(" ") match {
      case Array(n, m) => println(open(n.toInt, m.toInt))
    }
  }

  def open(n : Int, m : Int) : Int = {

    def isSecond(n : Int) = n % 2 == 0
    def isThird(n: Int) = n % 3 == 0

    def door(n: Int, even: Boolean) : Int = {
      n match {
        // first door is always open
        case x if x == 1 => OPEN
        // every second but not third door is close
        case x if isSecond(x) && !isThird(x) => CLOSE
        // every second and third door is open
        case x if isSecond(x) && isThird(x) =>  OPEN
        // every not second and not third door is always open
        case x if !isSecond(x) && !isThird(x) => OPEN
        // every third but not second door
        case x if !isSecond(x) && isThird(x) => if (even) CLOSE else OPEN
      }
    }

    // close last door, and return
    if (m == 1) return  n - 1

    val even : Boolean = m % 2 == 0

    // counter of unlocked doors
    var counter = 0

    for (i <- 1 to n) {
      counter = counter + door(i, even)
    }

    // check the state of the last door
    if (door(n, even) == OPEN) counter - 1 else counter + 1
  }
}

