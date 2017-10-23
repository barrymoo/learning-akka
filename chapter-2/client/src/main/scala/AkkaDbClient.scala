package com.akkadb.akkadbclient

import akka.actor.ActorSystem
import akka.util.Timeout
import akka.pattern.ask
import com.akkadb.dbserveractor.{GetRequest, SetRequest, QueryRequest}

import scala.concurrent.duration._

class AkkaDbClient(remoteAddress: String) {
  private implicit val timeout = Timeout(1 seconds)
  private implicit val system = ActorSystem("LocalSystem")
  private val remoteDb = system.actorSelection(s"akka.tcp://akkadb@$remoteAddress/user/akkadb-server")

  def set(key: String, value: Object) = {
    remoteDb ? SetRequest(key, value)
  }

  def get(key: String) = {
    remoteDb ? GetRequest(key)
  }

  def query(key: String) = {
    remoteDb ? QueryRequest(key)
  }
}

