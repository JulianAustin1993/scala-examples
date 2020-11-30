package com.github.julianaustin1993.programminginscala

import scala.io.Source


object LongLines {
  // Uses private method
  def processFile(filename: String, width: Int): Unit = {
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(filename, width, line)
  }

  private def processLine(filename: String, width: Int, line: String): Unit = {
    if (line.length > width)
      println(filename + ": " + line.trim)
  }
}

object LongLinesFP {
  // Using local functions
  def processFile(filename: String, width: Int): Unit = {
    def processLine(line: String): Unit = {
      if (line.length > width)
        println(2.toString + ": " + filename + ": " + line)
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}

object Ch8 extends App {
  // methods
  val width = args(0).toInt
  for (arg <- args.drop(1)) {
    LongLines.processFile(arg, width)
    LongLinesFP.processFile(arg, width)
  }

  // First class functions
  val increase = (x: Int) => {
    println("We are here")
    x + 1
  }
  println(increase(99))

  //foreach example
  val someNumbers = List(-11, -10, -5, 0, 5, 10)
  someNumbers.foreach((x: Int) => println(x))

  //filter example with shortform
  println(someNumbers.filter((x) => x > 0).length)
  // placeholder example
  println(someNumbers.filter(_ > 0).length)

  //partially applied functions
  def sum(a: Int, b: Int, c: Int) = a + b + c

  val a = sum _
  println(a.apply(1, 2, 3))
  val b = sum(1, _: Int, 3)
  println(b.apply(2))

  // closures
  val more = 10

  def addMore(x: Int): Int = x + more

  println(addMore(1))

  //Repeated args
  def echo(args: String*) = {
    for (arg <- args)
      println(arg)
  }

  echo()
  echo("One")
  echo("One", "Two", "Three")
  val arr = Array("What's", "up", "doc?")
  echo(arr: _*) //unpack a sequence

  // Tail recursion
  def boom(x: Int): Int =
    if (x == 0) throw new Exception("boom")
    else boom(x - 1) + 1 //Not tail recursive last operation is +1
  //boom(3)
  def bang(x: Int): Int =
    if (x == 0) throw new Exception("bang")
    else bang(x - 1)

  //bang(3)

}
