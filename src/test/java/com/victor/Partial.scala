package com.victor

object Partial {

  val pf = PartialFunction[Any, String] {
    case "huh" =>
      println("you win")
      "you win"
  }

}
