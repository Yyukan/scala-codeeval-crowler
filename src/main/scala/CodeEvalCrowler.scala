import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets

import org.openqa.selenium.{By, WebElement, WebDriver}
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest.selenium.WebBrowser
import scala.collection.JavaConverters._

/**
 * Crowler entry point
 */
object CodeEvalCrowler extends App with WebBrowser {

  implicit val webDriver: WebDriver = new ChromeDriver()

  val directory = "challenges/"

  println("CodeEval Crowler started...")

  if (args.length == 0) {
    usage()
    System.exit(-1)
  }

  go to "https://www.codeeval.com/accounts/login"

  waitUntilLoad()

  login(args(0), args(1))

  waitUntilLoad()

  go to "https://www.codeeval.com/open_challenge_scores/"

  waitUntilLoad()

  solvedChallenges().foreach(save)

  System.exit(0)

  /**
   * Shows simple usage
   */
  def usage() = {
    println("CodeEvalCrowler <codeeval_login> <codeeval_password>")
  }

  /**
   * Saves challenge code to file
   */
  def save(challenge: Challenge) = {
    println(s"Challenge " + challenge.name)

    println("Go to revisions " + challenge.link)
    go to challenge.link

    waitUntilLoad()

    val revision: WebElement = webDriver.findElement(By.xpath("//table[@id='viewscore-table']/tbody/tr[1]/td[1]/a"))

    println("Go to last revision " + challenge.link)
    go to revision.getAttribute("href")

    waitUntilLoad()

    Files.write(Paths.get(directory, challenge.filename()), code().getBytes(StandardCharsets.UTF_8))

    println("Challenge code saved into " + challenge.filename())
  }

  /**
   * Fills login form and submit
   */
  def login(username: String, password: String) = {
    textField("username").value = username
    pwdField("password").value = password
    submit()
  }

  /**
   * Returns ACE editor context by executing java script
   */
  def code(): String = {
    executeScript(
      """
         var editor = ace.edit("code-editor");
         return editor.getSession().getValue();
      """
    ).toString
  }

  /**
   * Returns all 'Solved' challenges
   */
  def solvedChallenges(): List[Challenge] = {
    val rows: List[WebElement] = webDriver.findElements(By.xpath("//table[@id='scores-table']//tbody//tr")).asScala.toList

    // filter rows by 'Solved' status and map first column to Challenge class
    val result = rows.filter(_.findElements(By.tagName("td")).asScala.toList(2).getText == "Solved").map(row => {

      val list: List[WebElement] = row.findElements(By.tagName("td")).asScala.toList
      val column: WebElement = list(0).findElement(By.tagName("a"))

      Challenge(column.getText, column.getAttribute("href"))
    })

    println("Found [" + result.size + "] solved challenges")

    result
  }

  def waitUntilLoad() {
    Thread.sleep(3000)
  }

  /**
   * Simple case class, contains challenge name and link
   */
  case class Challenge(name: String, link: String) {

    /**
     * Returns file name for challenge by replacing all
     * spaces with underscore and add 'scala' extension
     */
    def filename(): String = {
      name.replaceAll(" ", "_") + ".scala"
    }
  }

}