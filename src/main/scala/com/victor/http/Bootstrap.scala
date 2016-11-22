package com.victor.http

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http


object Bootstrap extends App{
 /* val sys = ActorSystem.create("webapp")
  val server = sys.actorOf(Props(new Server))
  implicit val system = sys
  IO(Http) ! Http.Bind(server, "0.0.0.0", port = 8080)*/
}
