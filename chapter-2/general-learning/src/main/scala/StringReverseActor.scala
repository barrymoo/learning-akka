package com.stringreverse.stringreverseactor

import akka.actor.{Actor, Status}
import akka.event.Logging

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
