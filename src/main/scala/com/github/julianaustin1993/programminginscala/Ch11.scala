package com.github.julianaustin1993.programminginscala

import scala.sys.error

object Ch11 extends App {
  // scala works mostly with equality
  val x = "abcd".substring(2)
  val y = "abcd".substring(2)
  println(x == y) //non-reference
  println(x eq y) // refernece equality

  //Nothing subtype helps for throwing errors.
  def divide(x: Int, y: Int): Int =
    if (y != 0) x / y
    else error("cant divide by zero") //Since nothing is a subclass of Int the above function is finde.

}
