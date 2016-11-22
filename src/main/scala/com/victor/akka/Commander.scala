package com.victor.akka

import akka.actor.Actor
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

class Commander extends Actor {

  val messenger = context.actorSelection("../messenger")
  var i:Int = 0;

  override def receive: Receive = {
    case "WorkDone" => {

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
