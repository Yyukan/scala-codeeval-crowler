


object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0).toList

  for (line <- lines)
  {
    line.toInt match {
      case x if x < 0 => println("This program is for humans")
      case x if 0 to 2 contains x => println("Home")
      case x if 3 to 4 contains x => println("Preschool")
      case x if 5 to 11 contains x => println("Elementary school")
      case x if 12 to 14 contains x => println("Middle school")
      case x if 15 to 18 contains x => println("High school")
      case x if 19 to 22 contains x => println("College")
      case x if 23 to 65 contains x => println("Work")
      case x if 68 to 100 contains x => println("Retirement")
      case x if x > 100 => println("This program is for humans")
    }
  }

}

