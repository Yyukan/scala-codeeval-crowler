import scala.collection.mutable
import scala.collection.mutable.{ HashMap, MultiMap, Set }

object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines: Iterator[String] = source.getLines.filter(_.length > 0)

  val network: mutable.ListBuffer[User] = new mutable.ListBuffer[User]

  for (line <- lines) {
    line split (":") match {
      case Array(a, b, c) => {
        network += User(a, b.split(",").toList, c.split(",").toList)
      }
      case Array(a, b) =>
        network += User(a, b.split(",").toList, Nil)
    }
  }

  suggestion(network.toList.sortBy(_.name))

  def suggestion(users: List[User]) : Unit = {
    val membership = new HashMap[String, Set[String]] with MultiMap[String, String]

    // create multi map where group contains all members
    users.foreach(user => {
      user.groups.foreach(group => membership.addBinding(group, user.name))
    })

    users.foreach(user => {

      val suggestions: mutable.Set[String] = new mutable.HashSet[String]

      membership.keySet.toList.sorted.foreach(group => {
        val members: Set[String] = membership.get(group).get

        if (!user.groups.contains(group)) {
          val fiftypercent = if (user.friends.size % 2 == 0) user.friends.size / 2 else user.friends.size / 2 + 1
          if (user.friends.filter(members.contains(_)).size >= fiftypercent) {
            suggestions += group
          }
        }
      })

      if (!suggestions.isEmpty) {
        println(user.name + ":" + suggestions.toList.sorted.mkString(","))
      }

    })
  }
}

case class User(name: String, friends: List[String], groups: List[String])

