import CodeEvalSite._

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

  System.exit(-1)
}


