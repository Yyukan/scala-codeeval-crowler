import java.util

import com.ning.http.client.cookie.Cookie
import com.ning.http.client.{FluentCaseInsensitiveStringsMap, Response}
import dispatch._, Defaults._
import scala.collection.mutable
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

  val loginResponse: Response = forward(CODE_EVAL_URL + CODE_EVAL_LOGIN)

  val req = url("https://www.codeeval.com/accounts/login/")

  val myPost: Req = req.secure.POST.setContentType("application/x-www-form-urlencoded", "UTF8")

  val myPostWithParams = myPost << Map("username" -> args(0), "password" -> args(1), "email_not_activated" -> "email",
    "next" -> "")

  cookies(loginResponse).foreach(cookie => myPostWithParams.addCookie(cookie))

  println(myPostWithParams)
  val result = Http(myPostWithParams > (x => x))

  val response: Response = result()
  println(response.getStatusCode)

  val req1 = url(response.getHeader("Location"))

  cookies(response).foreach(cookie => req1.addCookie(cookie))

  val result1 = Http(req1 > (x => x))

  val response1: Response = result1()
  println(response1.getStatusCode)

  //println(b.getResponseBody)

  System.exit(-1)
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

  def cookies(response : Response) : mutable.Buffer[Cookie] = {
    response.getHeaders("Set-Cookie").map((header: String) => {
      println(header)
      val split = header.split(";").map(_.trim)
      val name = split(0).split("=")(0)
      val value = split(0).split("=")(1)

      Cookie.newValidCookie(name, value, "", value, "/", 7200000000L, 31536000, true, true)
    })
  }

}


