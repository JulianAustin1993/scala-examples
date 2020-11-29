package com.github.julianaustin1993.programminginscala

import java.io.{FileNotFoundException, FileReader, IOException}
import java.net.{MalformedURLException, URL}

object Ch7 extends App {
  // If expression
  val filename = if (!args.isEmpty) args(0) else "default.txt"
  println(filename)

  // recursion
  def gcd(x: Long, y: Long): Long =
    if (y == 0) x else gcd(y, x % y)

  println("Greatest common divisor of %d and %d is %d".format(25, 15, gcd(25, 15)))

  // iteraton through collection
  val filesHere = (new java.io.File(
    "./src/main/scala/com/github/julianaustin1993/programminginscala")).listFiles()
  for (file <- filesHere)
    println(file)

  def fileLines(file: java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  // Mid stream assignment
  def grep(pattern: String) =
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches(pattern)
    } println(file + ": " + trimmed)

  grep(".*gcd.*")

  // New collecitons using yield
  def scalaFiles =
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
    } yield file

  // try catch
  try {
    val f = new FileReader("input.txt")
  } catch {
    case ex: FileNotFoundException => println("File not found") // Handle missing file
    case ex: IOException => // Handle other I/O error.
  }


  // try finally same as java.

  // yielding catch
  def urlFor(path: String) =
    try {
      new URL(path)
    } catch {
      case e: MalformedURLException => new URL("https://www.scala-lang.org")
    }

  println(urlFor("wjeh.vherhv.sh"))

  // match expressions
  val firstArg = if (args.length > 0) args(0) else ""

  val friend =
    firstArg match {
      case "salt" => "pepper"
      case "chips" => "salsa"
      case "eggs" => "bacon"
      case _ => "huh"
    }
  println(friend)

  // Multitable using fp style
  def makeRowSeq(row: Int) =
    for (col <- 1 to 10) yield {
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      padding + prod
    }

  def makeRow(row: Int) = makeRowSeq(row).mkString

  def multiTable() = {
    val tableSeq =
      for (row <- 1 to 10)
        yield makeRow(row)
    tableSeq.mkString("\n")
  }

  println(multiTable)

}
