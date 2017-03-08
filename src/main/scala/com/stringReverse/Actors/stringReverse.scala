package com.stringReverse.Actors

import akka.actor.{Actor, Status}
import com.stringReverse.messages.{ReversibleString, ReversedString}
import akka.event.Logging
/**
  * Akka actor which will reverse a string.
  *
  * This actor receives a ReversibleString message, reverses it and stores the string into
  * a val, then sends a ReversedString message to the sender.
  *
  * @author Tom Heuer on 3/7/17.
  */
class StringReverse extends Actor{
  val log = Logging(context.system, this)

  override def receive = {
    case ReversibleString(passedString) =>
      log.info("Received String - {} , Returned String - {}", passedString, passedString.reverse)
      println(passedString.reverse)
      sender() ! passedString.reverse

    case o =>
      Status.Failure(new ClassNotFoundException)
      log.info("Unknown Message Received")
      sender() ! "ERROR: Unknown Message"
  }

}
