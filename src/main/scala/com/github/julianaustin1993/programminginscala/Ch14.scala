package com.github.julianaustin1993.programminginscala

object Ch14 extends App {
  def sumMore(x: Int, y: Int): Int =
    if (y == 0) x
    else {
      x + y
    } ensuring (x < _) //ensuring on a condition.

  println(sumMore(1, 1))

  // ScalaTest and test suite exist but skipping.
}
