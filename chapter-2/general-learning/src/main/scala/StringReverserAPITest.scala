package com.stringreverse

import com.stringreverse.stringreverseactor.StringReverser

import scala.concurrent.Await
import scala.concurrent.duration._

object Main extends App {
  val stringReverser = new StringReverser
  val hello = stringReverser.run("hello")
  val world = stringReverser.run("world")
  val rev = Await.result(world, 1 seconds) + " " + Await.result(hello, 1 seconds)
  println(rev)

  val helloList = List("hello", "world")
  val listFutureString = for {
      s <- helloList
      f = stringReverser.run(s)
    } yield f

  println(listFutureString)

  stringReverser.stop
}
