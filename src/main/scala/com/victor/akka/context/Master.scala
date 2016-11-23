
package com.victor.akka.context

import akka.actor.{Actor, Props}
import akka.io.IO
import com.victor.akka.actors.{Commander, Communicator, Operator}
import com.victor.http.Server
import spray.can.Http

/**
  * 'context' actor where other children actors dwell in
  */
class Master extends Actor {

  val commander = context.actorOf(Props(new Commander()), "commander")
  val messenger = context.actorOf(Props(new Communicator()), "messenger")
  val worker = context.actorOf(Props(new Operator()), "worker")

  val httpServerHandler = context.actorOf(Props(new Server), "server")

  implicit val system = context.system
  IO(Http) ! Http.Bind(httpServerHandler, "0.0.0.0", port = 8080)
  println("http bind over")

  override def receive: Receive = {
    case _ => {

    }
  }
}
