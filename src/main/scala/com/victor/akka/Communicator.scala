package com.victor.akka

import akka.actor._
import akka.pattern.{ask, pipe}
import akka.util.Timeout

import scala.concurrent.duration._

class Communicator extends Actor {

  val worker = context.actorSelection("../worker")
  implicit val timeout = Timeout(1 second)
  import scala.concurrent.ExecutionContext.Implicits.global

  override def receive: Receive = {
    case o@"Request" => {
      println("before pipeTo")
      (worker ? o).pipeTo(sender())
      println("after pipeTo")
    }
  }
}
