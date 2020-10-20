package com.akka.actor

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.akka.messages.SetRequest
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._

class AkkaDbSpec extends FunSpecLike with Matchers{

  implicit  val system=ActorSystem()
  implicit val timeout=Timeout(5 seconds)

  describe("when akkaDb"){
    describe("is given  a request"){
      it("shoud save to key/value to the map val"){

        val actorRef=TestActorRef(new AkkaDb)
        actorRef  ! SetRequest("first","1")

        val actor = actorRef.underlyingActor
        actor.map.get("first") should equal(Some("1"))

      }

    }

  }


}
