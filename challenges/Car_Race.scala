/*
CHALLENGE DESCRIPTION:

In this challenge you need to find out how fast each car goes around a given track. A track is represented by a sequence
of float and integer numbers, each float number is a length of a straight section in miles,
followed by an integer number which represents an angle of the turn in degrees.
Each car is described by its top speed in MPH, the time to reach the top speed from 0 MPH, and the time to brake
from its top speed to 0 MPH. The rules:
1. A car is weightless, it is just a point.
2. A car accelerates and brakes with a constant acceleration.
3. Turn speed is linearly dependent on the turn degree and can be obtained from the following proportion:
A car can pass through a 0 degree turn with its top speed, and it must brake to 0 MPH if a turn degree is 180.
4. A car starts with 0 MPH speed, accelerates to its top speed, than goes at the top speed as long as possible,
than brakes as late as possible to reach the allowed turn speed, than after the turn immediately
accelerates from the turn speed to the top speed and so on.
5. No time is needed to pass a turn, it's just a point and at that point the speed of a car must be exactly the same as 
the allowed turn speed.
6. A length of a track section will always allow a car to reach it's top speed.
 */


object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  val track: Track = Track(lines.head)
  val cars = lines.tail.map(line => Car(line))

  val result: List[(Int, Double)] = cars.map(car => (car.number, car.lapTime(track)))

  result.sortBy(x => x._2).foreach( x => println("%d %.2f".format(x._1, x._2)))
}

case class Car(number: Int, topSpeed: Float, accelerateTime: Float, breakTime: Float) {

  val accelerateUp = topSpeed / accelerateTime
  val accelerateDown = topSpeed / breakTime

  def lapTime(track:Track): Double = {

    var beginSpeed = 0.0

    track.track.foldLeft(0.0)(
      (totalTime, element: (Float, Int)) => {

        val (length, angle) = element

        val timeAcceleration = (topSpeed - beginSpeed) / accelerateUp
        val distanceAcceleration = beginSpeed * timeAcceleration + accelerateUp * timeAcceleration * timeAcceleration / 2

        val speedEnd = turnSpeed(angle)

        val timeBreak = (topSpeed - speedEnd) / accelerateDown
        val distanceBreak = topSpeed * timeBreak - accelerateDown * timeBreak * timeBreak / 2

        val distanceTop = length - distanceAcceleration - distanceBreak
        val timeTop = distanceTop / topSpeed

        beginSpeed = speedEnd

        totalTime + timeAcceleration + timeTop + timeBreak
      }
    )
  }

  /* maximum allowed speed in the turn */
  def turnSpeed(angle: Int) = (1 - angle / 180.0) * topSpeed

}

case object Car {

  def apply(parameters: String): Car = {
    val Array(a, b, c, d) = parameters.split(" ")
    // mph to miles per second
    new Car(a.toInt, b.toFloat / 3600 , c.toFloat, d.toFloat)
  }
}

case class Track(track: List[(Float, Int)])

case object Track {
  def apply(parameters: String): Track = {
    val pairs = parameters.split(" ").sliding(2, 2).map {
      case Array(a, b) => (a.toFloat, b.toInt)
    }.toList
    new Track(pairs)
  }
}


