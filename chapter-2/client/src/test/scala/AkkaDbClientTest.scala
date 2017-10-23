package com.akkadb

import com.akkadb.akkadbclient.AkkaDbClient
import com.akkadb.dbserveractor.KeyNotFoundException
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

class AkkaDbClientTest extends FunSpecLike with Matchers {
  val client = new AkkaDbClient("127.0.0.1:2562")
  describe("akkaDbClient") {
    it("should set a value") {
      client.set("123", new Integer(123))
      val future = client.get("123")
      val result = Await.result(future, 10 seconds)
      result should equal(123)
    }
    it("should fail to get value") {
      intercept[KeyNotFoundException]{
        val future = client.get("456")
        val result = Await.result(future, 10 seconds)
      }
    }
    it("should return an empty set") {
      val future = client.query("456")
      val result = Await.result(future, 10 seconds)
      assert(result == Set())
    }
    it("should return an set of 123") {
      val future = client.query("2")
      val result = Await.result(future, 10 seconds)
      assert(result == Set("123"))
    }
  }
}
