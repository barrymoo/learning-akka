package com.akkadb

import com.akkadb.akkadbclient.AkkaDbClient
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

class AkkaDbClientTest extends FunSpecLike with Matchers {
  val client = new AkkaDbClient("127.0.0.1:2551")
  describe("akkaDbClient") {
    it("should set a value") {
      client.set("123", new Integer(123))
      val future = client.get("123")
      val result = Await.result(future, 10 seconds)
      result should equal(123)
    }
  }
}
