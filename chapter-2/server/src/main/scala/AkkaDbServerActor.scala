package com.akkadb.dbserveractor

import akka.actor.{Actor, Status}
import akka.event.Logging

import scala.collection.mutable

case class SetRequest(key: String, value: Object)
case class GetRequest(key: String)
case class QueryRequest(key: String)
case class KeyNotFoundException(key: String) extends Exception

class DbServerActor extends Actor {
  val map = new mutable.HashMap[String, Object]
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case SetRequest(key, value) => {
      log.info(s"Received SetRequest - key: $key, value: $value")
      map.put(key, value)
      sender() ! Status.Success
    }
    case GetRequest(key) => {
      log.info(s"Received GetRequest - key: $key")
      val response: Option[Object] = map.get(key)
      response match {
        case Some(a) => sender() ! a
        case None => sender() ! Status.Failure(new KeyNotFoundException(key))
      }
    }
    case QueryRequest(key) => {
      log.info(s"Received QueryRequest - key: $key")
      val response = for {
        k <- map.keys if k.contains(key)
      } yield k
      sender() ! response
    }
    case _ => Status.Failure(new ClassNotFoundException)
  }
}