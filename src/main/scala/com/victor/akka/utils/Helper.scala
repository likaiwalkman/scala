package com.victor.akka.utils

object Helper {
  //partial function definition
  def partialFunction:PartialFunction[Object, String] = {
    case "Over" => {
      println("over")
      "Over"
    }
  }

//  def main(args: Array[String]): Unit = {
//    println(Helper.partialFunction("Over"))
//  }
  //executionContext definition
  val executionContext = scala.concurrent.ExecutionContext.Implicits.global
}
