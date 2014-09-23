

object CodeEvalCrowler extends App {

  val CODE_EVAL_URL = "www.codeeval.com"
  val CODE_EVAL_LOGIN = "/accounts/login"

  println("CodeEval Crowler started...")

  if (args.length == 0) {
    usage()
    System.exit(-1)
  }

  println(s"Connecting $CODE_EVAL_URL$CODE_EVAL_LOGIN username ${args(0)}")


  def usage(): Unit = {
    println("CodeEvalCrowler <codeeval_login> <codeeval_password>")
  }
}


