import dispatch._, Defaults._

object CodeEvalCrowler extends App {

  val CODE_EVAL_URL = "http://www.codeeval.com"
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

  val svc = url(s"$CODE_EVAL_URL$CODE_EVAL_LOGIN")
  val country = Http(svc OK as.String)

  println(country)

}


