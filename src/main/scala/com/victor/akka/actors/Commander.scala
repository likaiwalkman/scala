package com.victor.akka.actors

import akka.actor.Actor
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * dispatcher
  */
class Commander extends Actor {

  val messenger = context.actorSelection("../messenger")
  var i: Int = 0;

  override def receive: Receive = {
    case "WorkDone" => {

    }
    case "Hello" =>{
      println("commander received Hello")
    }
    case "WorkReady" => {
      implicit val timeout = Timeout(5000 milliseconds)
      val outcome = Await.result(messenger ? "Request", 5000 milliseconds).asInstanceOf[String]
      outcome match {
        case "WorkDone" => {
          println("before send over")
          sender() ! "Over"
          println("after send over")
        }
      }
    }
  }
}
