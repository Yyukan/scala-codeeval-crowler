import java.util

import com.ning.http.client.cookie.Cookie
import com.ning.http.client.{RequestBuilder, Request, FluentCaseInsensitiveStringsMap, Response}
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

  // 301
  val request1 = url("https://www.codeeval.com/accounts/login").secure
  val response1 = doRequest(request1)
  // 301
  val request2 = addCookies(cookies(response1), url(response1.getHeader("Location")))
  val response2 = doRequest(request2)
  // 200 - login form
  val request3 = addCookies(cookies(response2), url(response2.getHeader("Location")))
  val response3 = doRequest(request3)

  // post login form
  val headers = Map(
    "User-Agent" -> "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/37.0.2062.120 Chrome/37.0.2062.120 Safari/537.36",
    "Referer" -> "https://www.codeeval.com/accounts/login/",
    "Origin" -> "https://www.codeeval.com",
    "Content-Type" -> "application/x-www-form-urlencoded",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Accept-Encoding" -> "gzip,deflate",
    "Accept-Language" -> "en-US,en;q=0.8,ru;q=0.6,uk;q=0.4",
    "Cache-Control" -> "max-age=0",
    "Connection" -> "keep-alive",
    "Content-Length" -> "66"
  )
  val params = Map("username" -> args(0), "password" -> args(1), "email_not_activated" -> "email", "next" -> "")

  // 302 location http://www.codeeval.com/dashboard/
  val request4 = addCookies(cookies(response3), url("https://www.codeeval.com/accounts/login/").POST << params <:<
    headers)
  val response4 = doRequest(request4)

  // 301
  val request5 = addCookies(cookies(response4), url("http://www.codeeval.com/dashboard/") <:< headers)
  val response5 = doRequest(request5)

  val request6 = addCookies(cookies(response4), url("https://www.codeeval.com/dashboard/").secure)
  val response6 = doRequest(request6)



  System.exit(-1)

  def doRequest(request: Req) : Response = {
    showRequest(request)

    val future: Future[Response] = Http(request)
    val response: Response = future()

    showResponse(response)

    response
  }

  def showRequest(req: Req) = {
    println(s"Request ===========================")
    val request : Request = req.toRequest

    println("Url: " + request.getUrl)
    println("Params: " + request.getParams)
    println("Headers: " + request.getHeaders)
    println("Cookies : " + request.getCookies)
  }

  def showResponse(response: Response) = {
    println(s"Response ===========================")
    println("Uri: " + response.getUri)
    println("Status code: " + response.getStatusCode)
    println("Content type: " + response.getContentType)

    val headers: FluentCaseInsensitiveStringsMap = response.getHeaders

    for (name <- headers.keySet()) {
      val value = headers.get(name)
      println("Header : [" + name + "] values [" + value.mkString + "]")
    }

    println("Response body size: " + response.getResponseBody.size)
  }

  def addCookies(cookies : mutable.Buffer[Cookie], req: Req) : Req = {
    var result = req
    cookies.foreach(cookie => result = result.addCookie(cookie))
    result
  }

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
    if (response.getHeader("Set-Cookie") == null) return mutable.Buffer[Cookie]()

    response.getHeaders("Set-Cookie").map((header: String) => {
      println(header)
      val split = header.split(";").map(_.trim)
      val name = split(0).split("=")(0)
      val value = split(0).split("=")(1)

      Cookie.newValidCookie(name, value, "", value, "/", 7200000000L, 31536000, true, true)
    })
  }

}


