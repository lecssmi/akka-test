package com.akka.actor

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import org.scalatest.{FunSpecLike, Matchers}

class ScalaPongActorSpec extends FunSpecLike with Matchers{
  implicit  val sysmtem=ActorSystem()

  describe("a scala  poing actor"){
    describe("received a message from other"){
      it("should send the sender a response "){
        val actor = TestActorRef(new ScalaPongActor("hello "))
        actor ! "ping "

      }

    }
  }

}
