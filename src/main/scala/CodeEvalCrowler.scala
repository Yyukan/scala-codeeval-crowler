import java.util

import com.ning.http.client.cookie.Cookie
import com.ning.http.client.{FluentCaseInsensitiveStringsMap, Response}
import dispatch._, Defaults._
import scala.concurrent.Await
import scala.util.{Success, Failure}
import scala.concurrent.duration._
import scala.xml.{Text, NodeSeq}
import scala.collection.JavaConversions._

object CodeEvalCrowler extends App {

  val CODE_EVAL_URL = "https://www.codeeval.com"
  val CODE_EVAL_LOGIN = "/accounts/login"

  def usage(): Unit = {
    println("CodeEvalCrowler <codeeval_login> <codeeval_password>")
  }

  println("CodeEval Crowler started...")

  if (args.length == 0) {
    usage()
    System.exit(-1)
  }

  println(s"Connecting $CODE_EVAL_URL$CODE_EVAL_LOGIN username ${args(0)}")

  Http.configure(_ setFollowRedirects true)

  val loginPage: Response = forward(CODE_EVAL_URL + CODE_EVAL_LOGIN)

  println(loginPage.getResponseBody.length)

  println(loginPage.getHeader("Location"))

  println(loginPage.getHeader("Set-Cookie"))

  val req = url(CODE_EVAL_URL + CODE_EVAL_LOGIN)

  def myPost: Req = req.secure.POST.setContentType("application/x-www-form-urlencoded", "UTF8")


  def myPostWithParams = myPost << Map("username" -> args(0), "password" -> args(1), "email_not_activated" -> "email", "next" -> "")


  //val cookie: Cookie = new Cookie()

  //myPostWithParams.addCookie(cookie)
  val result = Http(myPostWithParams > (x => x))

  val response: Response = result()
  println(response.getStatusCode)
  val b = forward(response.getHeader("Location"))
  println(b.getResponseBody)

  /**
   * Forwards until login page is reached
   */
  def forward(link: String): Response = {
    val svc = url(link)

    val result = Http(svc > (x => x))
    val response: Response = result()

    println(s"Connecting $link ... ${response.getStatusCode}" )

    if (response.getStatusCode == 301) forward(response.getHeader("Location"))
    else response
  }


}


