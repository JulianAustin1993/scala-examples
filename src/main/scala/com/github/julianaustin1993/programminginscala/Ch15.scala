package com.github.julianaustin1993.programminginscala

// Simple case class example
sealed abstract class Expr //selaed class means the compiler will check we have covered all cases.
case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object Ch15 extends App {
  val v = Var("X")
  val op = BinOp("+", Number(1), v)
  println(v.name) // provided by case class as implicit val
  println(op) //provided through case class a nice toString method as well as hashCode, and equals.
  println(op.right == Var("X"))
  println(op.copy(operator = "-")) //copy operator provided by case class

  // Pattern matching can be done  on case classes.
  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e //double negation
    case BinOp("+", e, Number(0)) => e //adding zero
    case BinOp("*", e, Number(1)) => e //multiplying by 1
    case _ => expr
  }

  println(simplifyTop(BinOp("+", Number(2), Number(0))))

  def simplifyAdd(e: Expr): Expr = e match {
    case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2)) //patttern guard.
    case _ => e
  }

  println(simplifyAdd(BinOp("+", Number(3), Number(3))))
  println(simplifyAdd(BinOp("*", Number(2), Number(1))))

  //Option class
  val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

  println(show(capitals get "France"))
  println(show(capitals get "UK"))

  //Paterns in variable def
  val myTuple = (123, "abc")
  val (number, string) = myTuple
  println(number)

  //Patterns in loops
  val results = List(Some("apple"), None, Some("orange"))
  for (Some(fruit) <- results) println(fruit)
}
