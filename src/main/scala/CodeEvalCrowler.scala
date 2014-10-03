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

  def usage() = {
    println("CodeEvalCrowler <codeeval_login> <codeeval_password>")
  }

  println("CodeEval Crowler started...")

  if (args.length == 0) {
    usage()
    System.exit(-1)
  }


  CodeEvalSite.login(args(0), args(1))

  println(CodeEvalSite.page("open_challenge_scores/"))

//  // request login form
//  val request1 = url("https://www.codeeval.com/accounts/login").secure
//  val response1 = doRequest(request1)
//
//  // response is 301
//  val request2 = addCookies(cookies(response1), url(response1.getHeader("Location")))
//  val response2 = doRequest(request2)
//
//  // response is 200 - login form
//  val request3 = addCookies(cookies(response2), url(response2.getHeader("Location")))
//  val response3 = doRequest(request3)
//
//  // post login form
//  val headers = Map(
//    "User-Agent" -> "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/37.0.2062.120 Chrome/37.0.2062.120 Safari/537.36",
//    "Referer" -> "https://www.codeeval.com/accounts/login/",
//    "Origin" -> "https://www.codeeval.com",
//    "Content-Type" -> "application/x-www-form-urlencoded",
//    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
//    "Accept-Encoding" -> "gzip,deflate",
//    "Accept-Language" -> "en-US,en;q=0.8,ru;q=0.6,uk;q=0.4",
//    "Cache-Control" -> "max-age=0",
//    "Connection" -> "keep-alive",
//    "Content-Length" -> "66"
//  )
//  val params = Map("username" -> args(0), "password" -> args(1), "email_not_activated" -> "email", "next" -> "")
//
//  // 302 location http://www.codeeval.com/dashboard/
//  val request4 = addCookies(cookies(response3), url("https://www.codeeval.com/accounts/login/").POST << params <:< headers)
//  val response4 = doRequest(request4)
//
//  // 301
//  val headers2 = Map(
//    "User-Agent" -> "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/37.0.2062.120 Chrome/37.0.2062.120 Safari/537.36",
//    "Content-Type" -> "text/html; charset=utf-8",
//    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
//    "Accept-Encoding" -> "gzip,deflate",
//    "Accept-Language" -> "en-US,en;q=0.8,ru;q=0.6,uk;q=0.4",
//    "Cache-Control" -> "max-age=0",
//    "Connection" -> "keep-alive"
//  )
//  val request5 = addCookies(cookies(response4), url(response4.getHeader("Location")) <:< headers2)
//  val response5 = doRequest(request5)
//
//  // 200 OK DASHBOARD body !!!
//  val request6 = addCookies(cookies(response4), url(response5.getHeader("Location")) <:< headers2)
//  val response6 = doRequest(request6)
//
//  // 200 OK challenges
//  val request7 = addCookies(cookies(response4), url("https://www.codeeval.com/open_challenge_scores/") <:< headers2)
//  val response7 = doRequest(request7)
//
//  println(response7.getResponseBody)

  System.exit(-1)


}


