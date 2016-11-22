package com.victor.akka

import java.util.concurrent.TimeUnit

import akka.actor.Actor

class Operator  extends  Actor{
  override def receive: Receive = {
    case "Request" =>{
      TimeUnit.MILLISECONDS.sleep(1000)
      println("sleep 1s")
      sender ! "WorkDone"
      println("awaken")
    }
  }
}
