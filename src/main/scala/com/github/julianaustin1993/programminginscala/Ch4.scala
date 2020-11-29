package com.github.julianaustin1993.programminginscala

import scala.collection.mutable.Map

class CheckSumAccumulator {
  private var sum = 0

  def add(b: Byte) {
    sum += b
  }

  def checksum(): Int = ~(sum & 0xFF) + 1
}

object CheckSumAccumulator {
  private val cache = Map[String, Int]()

  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new CheckSumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum
      cache += (s -> cs)
      cs
    }
}

object Ch4 extends App {
  for (season <- "Fall" :: "Winter" :: "Spring" :: Nil)
    println(season + ": " + CheckSumAccumulator.calculate(season))
}
