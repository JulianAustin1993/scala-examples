package com.github.julianaustin1993.programminginscala

import java.io.{File, PrintWriter}

import com.github.julianaustin1993.programminginscala.FileMatcher.filesEnding


// Closures to reduce code duplication
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles

  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file

  def filesEnding(query: String) =
    filesMatching(_.endsWith(query))

  def filesContaining(query: String) =
    filesMatching(_.contains(query))

  def filesRegex(query: String) =
    filesMatching(_.matches(query))
}

object Ch9 extends App {
  val arr = filesEnding(".rst")
  arr.foreach(println)

  //Currying
  def plainSum(x: Int, y: Int) = x + y

  println(plainSum(1, 2))

  def curriedSum(x: Int)(y: Int) = x + y

  println(curriedSum(1)(2))

  def onePlus = curriedSum(1) _

  println(onePlus(2))

  //loan pattern
  def withPrintWriter(file: File)(op: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  // Nice loan pattern to write a file.
  val file = new File("date.txt")
  withPrintWriter(file) {
    writer => writer.println(new java.util.Date)
  }
  file.delete() //cleaning file up.


}
