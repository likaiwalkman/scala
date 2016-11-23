package com.victor.akka.utils

object Helper {
  //partial function definition
  val partialFunction = PartialFunction[Any, String] {
    case "Over" =>
      println("terminated")
      "you win"
  }


  /*def main(args: Array[String]): Unit = {
    println(Helper.partialFunction("Over"))
    println(Helper.partialFunction)
  }*/

  //executionContext definition
  val executionContext = scala.concurrent.ExecutionContext.Implicits.global
}
