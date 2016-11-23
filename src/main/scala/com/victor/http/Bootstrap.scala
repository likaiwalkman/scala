package com.victor.http

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import com.victor.akka.context.Master
import spray.can.Http

object Bootstrap extends App{
  val sys = ActorSystem.create("webapp")
  val server = sys.actorOf(Props(new Master))

}
