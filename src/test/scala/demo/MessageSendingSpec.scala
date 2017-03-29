package demo

import akka.actor.{Actor, Props, _}
import akka.testkit._
import org.scalatest.WordSpecLike

case object MyMessage
case object AnotherMessage

class MyActor extends Actor {
  val anotherActor = createAnother
  def receive: Receive = {
    case MyMessage =>
      println("MyActor send AnotherMessage to child AnotherActor")
      anotherActor ! AnotherMessage
  }

  def createAnother = context.actorOf(Props[AnotherActor])
}

class AnotherActor extends Actor{
  def receive = {
    case _ => println("""AnotherActor received $_""")
  }
}

class MessageSendingSpec extends TestKit(ActorSystem("test")) with WordSpecLike{

  val probe = TestProbe()
  val myActor = TestActorRef(new MyActor{
    override def createAnother = probe.ref
  })
  "Sending MyMessage to an instance of MyActor" should {
    "pass AnotherMessage to the child AnotherActor" in {
      myActor ! MyMessage
      probe.expectMsg(AnotherMessage)
    }
  }
}
