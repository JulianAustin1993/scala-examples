package com.github.julianaustin1993.programminginscala

object Ch16 extends App {
  // merge sort
  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }

    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }

  println(msort((x: Int, y: Int) => x < y)(List(5, 7, 1, 3)))

  //mapping over lists
  val words = List("The", "quick", "brown", "fox")
  println(words map (_.toList.reverse.mkString))

  //flatmap
  println(words flatMap (_.toList))

  //filtering
  println(words filter (_.length == 3))

  //partitioning
  println(List.range(1, 6) partition (_ % 2 == 0))

  // find returns option first element
  println(List.range(1, 6) find (_ % 2 == 0))

  //folding
  println(words.foldLeft("")(_ + " " + _))

  // processing multiple list together
  println(List.range(1, 6).lazyZip(List.range(1, 6).map((x) => 10 * x)).map(_ * _))

  //type inference
  val abcde = List("a", "b", "c", "d", "e")

  //msort(_ > _)(abcde) crashes because msort cant infer the type _>_ as its checked first.
  def msort2[T](xs: List[T])(less: (T, T) => Boolean): List[T] = {
    msort(less)(xs)
  }

  println(msort2(abcde)(_ > _)) //works becasue we have an concrete type first.
}
