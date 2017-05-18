package fsm

import akka.actor.{ActorSystem, FSM, Props}
import akka.testkit.TestKit
import org.scalatest._

import scala.collection.immutable

object FSMDocSpec {
  // messages and data types
}

class FSMDocSpec extends TestKit(ActorSystem("system")) with WordSpecLike  {

  // fsm code elided ...

  "simple finite state machine" must {

    "demonstrate NullFunction" in {
      class A extends FSM[Int, Null] {
        val SomeState = 0
        when(SomeState)(FSM.NullFunction)
      }
    }

    "batch correctly" in {
      val buncher = system.actorOf(Props(classOf[Buncher], this))
      buncher ! SetTarget(testActor)
      buncher ! Queue(42)
      buncher ! Queue(43)
      expectMsg(Batch(immutable.Seq(42, 43)))
      buncher ! Queue(44)
      buncher ! Flush
      buncher ! Queue(45)
      expectMsg(Batch(immutable.Seq(44)))
      expectMsg(Batch(immutable.Seq(45)))
    }

    "not batch if uninitialized" in {
      val buncher = system.actorOf(Props(classOf[Buncher], this))
      buncher ! Queue(42)
      expectNoMsg
    }
  }
}