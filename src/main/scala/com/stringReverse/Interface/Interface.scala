package com.stringReverse.Interface

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.stringReverse.Actors.StringReverse
import com.stringReverse.messages.ReversibleString

import scala.concurrent.Future
import scala.concurrent.duration._

/**
  * Interface for interacting with the reverse string actor
  *
  * @author Tom Heuer on 3/7/16
  *
  *
  */
class Interface {

  /** Returns a future string which will contain the reverse of the string passed to it */
  def revString(inputString: String): Future[String] = {
    val actorSystem = ActorSystem("stringReversing")
    val stringReverseActor = actorSystem.actorOf(Props[StringReverse],"sRevActor")
    implicit val timeout = Timeout(5 seconds)
    val future = ask(stringReverseActor, ReversibleString(inputString)).mapTo[String]
    future
  }
}
