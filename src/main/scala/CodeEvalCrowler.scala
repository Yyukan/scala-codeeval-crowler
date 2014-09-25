import uk.co.bigbeeconsultants.http.{HttpBrowser, HttpClient}
import uk.co.bigbeeconsultants.http.header.HeaderName
import uk.co.bigbeeconsultants.http.response.Response
import java.net.URL

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

  val loginResponse = forward(CODE_EVAL_URL + CODE_EVAL_LOGIN)
  println("Headers")
  loginResponse.headers.foreach( x => println(x))
  println("Cookies")
  loginResponse.cookies.foreach( x => println(x) )


//  println(loginPage.getHeaders)
//  println(loginPage.getHeader("Set-Cookie"))
//
//  val loginRequest = url(CODE_EVAL_URL + CODE_EVAL_LOGIN)
//    .secure.POST.setContentType("application/x-www-form-urlencoded", "UTF8") << Map("username" -> args(0),
//    "password" -> args(1),
//    "email_not_activated" -> "email", "next" -> "")



  //val cookie: Cookie = toSessionCookie(loginPage.getHeader("Set-Cookie"))

  //loginRequest.addCookie(cookie)

//  println(loginPage.getCookies)
//
//  System.exit(-1)
//
//  val result = Http(loginRequest > (x => x))
//
//  val response: Response = result()
//  println(response.getStatusCode)
//  val b = forward(response.getHeader("Location"))
  //println(b.getResponseBody)

//  def toSessionCookie(string : String) : Any = {
//    string.split(";") match {
//      case Array(a, b, c, d, e) =>
    //    public static Cookie newValidCookie(String name, String value, String domain, String rawValue, String path,
    //  long expires, int maxAge, boolean secure, boolean httpOnly) {

          //Cookie.newValidCookie()
//    }
//  }

  /**
   * Forwards until login page is reached
   */
  def forward(link: String): Response = {
    val httpClient = new HttpClient()
    val response: Response = httpClient.get(new URL(link))
    println(response.status)
    //println(response.body.asString)

    println(s"Connecting $link ... ${response.status}" )

    if (response.status.code.toInt == 301) {
      forward(response.headers.get(new HeaderName("Location")).get.toString())
    } else {
      response
    }
    //    val request = url(link)
    //
    //    val result = Http(request > (x => x))
    //    val response: Response = result()
    //
    //    println(s"Connecting $link ... ${response.getStatusCode}" )
    //
    //    if (response.getStatusCode == 301) forward(response.getHeader("Location"))
    //    else response
    //  }
  }

}


