package com.akkademy

import akka.actor.{Actor, ActorContext}
import akka.event.Logging

import scala.collection.mutable.HashMap
import com.akkademy.messages.SetRequest

import scala.collection.mutable

class AkkademyDb extends Actor {
  val map = new mutable.HashMap[String, Object]
  val log = Logging(context.system, this)
  override def receive = {
    case SetRequest(key, value) => {
      log.info(s"Received SetRequest - $key: $value")
      map.put(key, value)
    }
    case o => log.info(s"Received unknown message $o")
  }
}