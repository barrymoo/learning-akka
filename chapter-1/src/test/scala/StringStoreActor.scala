package com.akkademy

import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}
import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.akkademy.stringactor.StringStoreActor

class StringStoreActorSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("stringStoreActor") {
    describe("given String") {
      it("should place string into s") {
        val actorRef = TestActorRef(new StringStoreActor)
        actorRef ! "Hello World"
        val stringActor = actorRef.underlyingActor
        stringActor.s should equal("Hello World")
      }
    }

    describe("given 2 Strings") {
      it("should hold last s") {
        val actorRef = TestActorRef(new StringStoreActor)
        actorRef ! "One"
        actorRef ! "Two"
        val stringActor = actorRef.underlyingActor
        stringActor.s should equal("Two")
      }
    }
  }
}
