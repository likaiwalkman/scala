
package com.victor.akka.context

import akka.actor.{Actor, Props}
import com.victor.akka.actors.{Commander, Communicator, Operator}

/**
  * 'context' actor where other children actors dwell in
  */
class Master extends Actor {

  val commander = context.actorOf(Props(new Commander()), "commander")
  val messenger = context.actorOf(Props(new Communicator()), "messenger")
  val worker = context.actorOf(Props(new Operator()), "worker")

  override def receive: Receive = {
    case _ => {

    }
  }
}
