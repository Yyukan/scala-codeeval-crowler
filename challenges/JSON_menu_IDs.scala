/**
CHALLENGE DESCRIPTION:

  You have JSON string which describes a menu. Calculate the SUM of IDs of all "items" in case a "label" exists for an item.

INPUT SAMPLE:

  Your program should accept as its first argument a path to a filename. Input example is the following

{"menu": {"header": "menu", "items": [{"id": 27}, {"id": 0, "label": "Label 0"}, null, {"id": 93}, {"id": 85}, {"id": 54}, null, {"id": 46, "label": "Label 46"}]}}

{"menu": {"header": "menu", "items": [{"id": 81}]}}

{"menu": {"header": "menu", "items": [{"id": 70, "label": "Label 70"}, {"id": 85, "label": "Label 85"}, {"id": 93, "label": "Label 93"}, {"id": 2}]}}
All IDs are integers between 0 and 100. It can be 10 items maximum for a menu.

OUTPUT SAMPLE:

  Print results in the following way.

46
0
248
  */
object Main extends App {
  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)

  for (line <- lines) {
    println(jsonMenuId(line))
  }

  def jsonMenuId(json: String): Int = {
    var result = 0
    val pattern = """\"id\":\s*(\d+)\s*,\s*\"label\"""".r
    pattern.findAllIn(json).matchData foreach {
      m => result += Integer.parseInt(m.group(1))
    }

    result
  }

}