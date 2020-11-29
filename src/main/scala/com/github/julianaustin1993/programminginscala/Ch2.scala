package com.github.julianaustin1993.programminginscala

object Ch2 extends App {
  println(1 + 2) //interpolator
  val msg = "Hello, World!"
  println(msg)
  val msg2: java.lang.String = "Hello again, world!"
  println(msg2)
  val msg3: String = "Hello again, world! Second" //Simple name for java class.
  println(msg3)
  var msg4: String = "Variable greeting."
  println(msg4)
  msg4 = "Changed" //Var are mutable. Would fail if msg4 was a val.
  println(msg4)

  /** Functions * */
  def max(x: Int, y: Int): Int = if (x > y) x else y

  println("Max of %d, %d is %d".format(3, 5, max(3, 5)))

  /** Imperative * */
  println("Imperative loop.")
  var i = 0
  while (i < args.length) {
    println(args(i))
    i += 1
  }
  println("Done")

  /** Functional * */
  println("Foreach functional loop")
  args.foreach((arg: String) => println(arg))
  println("Done")
  println("Fucntional for loop")
  for (arg <- args)
    println(arg)
  println("Done")

}
