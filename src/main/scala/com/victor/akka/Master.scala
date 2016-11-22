
package com.victor.akka
import akka.actor.{Actor, Props}
import com.victor.akka.{Commander, Communicator, Operator}

class Master extends Actor{

  val commander = context.actorOf(Props(new Commander()), "commander")
  val messenger = context.actorOf(Props(new Communicator()), "messenger")
  val worker = context.actorOf(Props(new Operator()), "worker")

  override def receive: Receive = {
    case _ => {

    }
  }
}
