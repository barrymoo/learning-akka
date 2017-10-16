package com.akkademy.stringactor

import akka.actor.Actor

class StringStoreActor extends Actor {
  var s: String = ""

  override def receive: Receive = {
    case x: String => {
      s = x
    }
    case _ => {
      s = ""
    }
  }
}