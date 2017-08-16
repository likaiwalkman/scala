import scala.annotation.tailrec

object TailRecScala {
  def main(args: Array[String]): Unit = {
    val rs = fact(10)
    println(rs)
  }

  def fact(n: Int) = factorial_tail(n, 1, 2)

  @tailrec
  def factorial_tail(n: Int, acc1: Int, acc2: Int): Int = {
    if (n < 2)
      acc1
    else
      factorial_tail(n - 1, acc2, acc1 + acc2)
  }

}
