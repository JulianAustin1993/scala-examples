package com.github.julianaustin1993.programminginscala

import scala.collection.immutable.HashSet
import scala.io.Source

object Ch3 extends App {
  //Parameterizing an array.
  val greetStrings = new Array[String](3)
  greetStrings(0) = "Hello"
  greetStrings(1) = ", "
  greetStrings(2) = "world!\n"
  for (i <- 0 to 2)
    println(greetStrings(i))

  // Init an array
  val numNames = Array("zero", "one", "two")
  println(numNames.mkString("Array(", ", ", ")"))
  val numNames2 = Array.apply("zero", "one", "two")
  println(numNames2.mkString("Array(", ", ", ")"))

  //List
  val oneTwo = 1 :: 2 :: Nil
  val threeFour = 3 :: 4 :: Nil //Another way to add to list.
  val oneTwoThreeFour = oneTwo ::: threeFour
  println(oneTwo + " and " + threeFour + " were not mutated")
  println("Thus, " + oneTwoThreeFour + " is a new list.")

  //tuple
  val pair = (99, "luftballons")
  println(pair._1)
  println(pair._2)

  // sets
  var jetSet = Set("Boeing", "Airbus") //can utilise a mutable set by importing it.
  jetSet += "Lear"
  println(jetSet.contains("Cessna"))

  // Hash set
  val hashSet = HashSet("Tomatoes", "Chillies")
  println(hashSet + "Corriander")

  //Maps
  val romanNumeral = Map(
    1 -> "I", 2 -> "II", 3 -> "III",
    4 -> "IV", 5 -> "V")
  println(romanNumeral(4))

  // More fp
  def formatArgs(args: Array[String]) = args.mkString("\n")

  println(formatArgs(args))

  // Reading File
  if (args.length > 0) {
    for (line <- Source.fromFile(args(0)).getLines())
      println(line.length + " " + line)
  }
  else
    Console.err.println("Please enter filename")
}
