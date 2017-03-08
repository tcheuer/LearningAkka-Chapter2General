package com.stringReverse

import akka.util.Timeout
import com.stringReverse.Interface.Interface

import scala.concurrent.Await
import scala.concurrent.duration._


object Main {
  def main(args: Array[String]): Unit = {

    val toReverse = "Hello, world!"
    val revObj = new Interface()

    implicit val timeout = Timeout(5 seconds)
    val future = revObj.revString(toReverse)
    val result = Await.result(future , 1 second)

    println("In main result: " + result)

    System.exit(0)
  }
}
