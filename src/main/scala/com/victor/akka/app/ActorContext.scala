package com.victor.akka.app

import akka.actor.{ActorSystem, Props}
import com.victor.akka.context.Master

/**
  * very simple demonstration to show how actors act
  * Created by likai on 2016/11/21.
  */
object ActorContext extends App {
  val system = ActorSystem("demo")
  val context = system.actorOf(Props(new Master), "master")
  val commander = system.actorSelection("user/master/commander")
}
