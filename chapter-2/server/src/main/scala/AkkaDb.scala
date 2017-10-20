package com.akkadb.akkadb

import akka.actor.{ActorSystem, Props}
import com.akkadb.dbserveractor.DbServerActor

object Main extends App {
  val system = ActorSystem("akkadb")
  system.actorOf(Props[DbServerActor], name = "akkadb-server")
}