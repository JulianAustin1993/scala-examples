package com.github.julianaustin1993.programminginscala

import scala.collection.mutable.ArrayBuffer

//Traits use the keyword trait, can mixin to classes.
trait Philosophical {
  def philosophize(): Unit = {
    println("I consume memory, therefore I am!")
  }
}

class Animal

trait HasLegs

class Frog extends Animal with Philosophical with HasLegs { //with keyword to mixin multiple traits and classes
  override def toString = "green"

  override def philosophize(): Unit = {
    println("It ain't easy being " + toString + "!")
  }
}


// Ordered trait
class Rational12(n: Int, d: Int) extends Ordered[Rational12] {
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

  def compare(that: Rational12): Int = (this.numer * that.denom) - (that.numer * this.denom)

}

/**
 * Traits are stackable
 */

abstract class IntQueue {
  def get(): Int

  def put(x: Int)
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]

  override def get(): Int = buf.remove(0)

  override def put(x: Int): Unit = {
    buf += x
  }
}

trait Doubling extends IntQueue {
  abstract override def put(x: Int) {
    super.put(2 * x)
  }
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) {
    super.put(x + 1)
  }
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) {
    if (x >= 0) super.put(x)
  }
}

object Ch12 extends App {
  val frog = new Frog
  println(frog.toString)
  frog.philosophize()
  val phrog: Philosophical = frog //traits also define a type
  phrog.philosophize()

  val half = new Rational12(1, 2)
  val twoSixth = new Rational12(2, 6)
  println(half >= twoSixth)

  val queue1 = new BasicIntQueue with Doubling
  queue1.put(10)
  println(queue1.get())
  val queue = (new BasicIntQueue with Incrementing with Filtering)
  queue.put(-1)
  queue.put(0)
  queue.put(1)
  println(queue.get())
  println(queue.get())
}
