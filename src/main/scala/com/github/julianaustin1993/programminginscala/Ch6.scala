package com.github.julianaustin1993.programminginscala

class Rational(n: Int, d: Int) {
  require(d != 0) //Stop rationals having 0 denominator.

  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n: Int) = this(n, 1) // Constructor for integers with 1 divisor.

  def +(that: Rational): Rational =
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def +(i: Int): Rational =
    new Rational(numer + i * denom, denom)

  def -(that: Rational): Rational =
    new Rational(numer * that.denom - that.numer * denom, denom * that.denom)

  def -(i: Int): Rational =
    new Rational(numer - i * denom, denom)

  def *(that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  def *(i: Int): Rational =
    new Rational(numer * i, denom)

  def /(that: Rational): Rational =
    new Rational(numer * that.denom, denom * that.numer)

  def /(i: Int): Rational =
    new Rational(numer, denom * i)

  override def toString = numer + "/" + denom

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}

object Ch6 extends App {
  implicit def intToRational(x: Int) = new Rational(x) //creating implicit conversion.

  val half = new Rational(1, 2)
  val twoSixth = new Rational(2, 6)

  println(half)
  println(twoSixth * 4)
  println(half - twoSixth)
  println(half / twoSixth)
  println(2 * half)

}
