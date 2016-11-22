package com.victor.akka.app

import akka.actor.{ActorSystem, Props}
import akka.pattern._
import akka.util.Timeout
import com.victor.akka.context.Master

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * very simple demonstration to show how actors act
  * Created by likai on 2016/11/21.
  */
object AppEntrance1 extends App {
  val system = ActorSystem("demo")
  val context = system.actorOf(Props(new Master), "master")
  val commander = system.actorSelection("user/master/commander")
  implicit val timeout = Timeout(5000 milliseconds)
  val outcome = Await.result(commander ? "WorkReady", 5000 milliseconds).asInstanceOf[String]
  outcome match {
    case "Over" => {
      println("over")
    }
  }
}
