package com.pong

import org.scalatest.{FunSpecLike, Matchers}
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.pong.pongactor.PongActor

import scala.concurrent.Await
import scala.concurrent.duration._

class PongActorAskTest extends FunSpecLike with Matchers {
  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)
  val pongActor = system.actorOf(Props(classOf[PongActor]))
  describe("Pong Actor") {
    it("should respond with Pong") {
      val future = pongActor ? "Ping"
      val result = Await.result(future.mapTo[String], 1 second)
      assert(result == "Pong")
    }

    it("should fail on unknown receive") {
      val future = pongActor ? "Derp"
      intercept[Exception]{
        Await.result(future.mapTo[String], 1 second)
      }
    }
  }
}