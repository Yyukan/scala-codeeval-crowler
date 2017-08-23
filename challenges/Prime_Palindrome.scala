object Main extends App {

  def isPalindrome(n: Int): Boolean = n.toString.reverse == n.toString

  def isPrime(n: Int): Boolean = {
    if (n <= 1)
      false
    else if (n == 2)
      true
    else
      !(2 to (n - 1)).exists(x => n % x == 0)
  }

  println((1 to 1000).filter(isPalindrome(_)).filter(isPrime(_)).max)
}