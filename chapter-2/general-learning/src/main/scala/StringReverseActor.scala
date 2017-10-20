package com.stringreverse.stringreverseactor

import akka.actor.{Actor, ActorSystem, Props, Status}
import akka.event.Logging
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.duration._

class StringReverseActor extends Actor {
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case x: String => {
      val reversed = x.reverse
      log.info(s"Received $x sending $reversed")
      sender() ! reversed
    }
    case _ => sender() ! Status.Failure
  }
}

class StringReverser {
  private implicit val timeout = Timeout(1 seconds)
  private implicit val system = ActorSystem("Reverser")
  private val reverseActor = system.actorOf(Props(classOf[StringReverseActor]))
  def run(s: String) = {
    reverseActor ? s
  }
  def stop = {
    system.terminate()
  }
}
