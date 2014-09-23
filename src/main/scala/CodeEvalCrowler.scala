import com.ning.http.client.Response
import dispatch._, Defaults._
import scala.util.{Success, Failure}

object CodeEvalCrowler extends App {

  val CODE_EVAL_URL = "www.codeeval.com"
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

  val codeEval = host(CODE_EVAL_URL).secure
  val loginRequest = codeEval / "accounts" / "login"


  val response: Future[Response] = Http(loginRequest)

  response onComplete {
    case Success(a) => println("Success + " + a.getStatusText + " " + a.getStatusCode)
    case Failure(t) => println("An error has occured: " + t.getMessage)
  }





}


