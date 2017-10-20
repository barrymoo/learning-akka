package com.stringreverse

import akka.actor.{ActorSystem, Props, Status}
import akka.util.Timeout
import akka.pattern.ask
import com.stringreverse.stringreverseactor.StringReverseActor
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

class StringReverseActorTest extends FunSpecLike with Matchers {
  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)
  val stringReverseActor = system.actorOf(Props(classOf[StringReverseActor]))
  describe("StringReverseActor") {
    it("should reverse string") {
      val future = stringReverseActor ? "hello"
      val response = Await.result(future.mapTo[String], 1 seconds)
      assert(response == "olleh")
    }
    it("should return Status.Failure") {
      val future = stringReverseActor ? new Integer(123)
      val response = Await.result(future, 1 seconds)
      assert(response == Status.Failure)
    }
  }
}
