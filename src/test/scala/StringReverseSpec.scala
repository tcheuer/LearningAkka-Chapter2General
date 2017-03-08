import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.stringReverse.Actors.StringReverse
import com.stringReverse.messages.ReversibleString
import org.scalatest.{FunSpecLike, Matchers}
import akka.pattern.ask

import scala.concurrent.{Await, Future }
import scala.concurrent.duration._
import scala.Seq

/**
  * @author Tom Heuer on 3/7/17.
  */
class StringReverseSpec extends FunSpecLike with Matchers {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)
  val testString = "Hello, world."

  describe("StringReverser") {
    describe("given ReversibleString") {
      it("should return the reversed string in a message") {

        val actorRef = TestActorRef(new StringReverse)


        val future = ask(actorRef, ReversibleString(testString)).mapTo[String]
        val result = Await.result(future, 1 second)

        result should equal(testString.reverse)

      }
    }
    describe("given anything else") {
      it("should return a failure message") {

        val actorRef = TestActorRef(new StringReverse)


        val future = ask(actorRef, "Yo").mapTo[String]
        val result = Await.result(future, 1 second)

        result should equal("ERROR: Unknown Message")

      }
    }
  }
  describe("running through a list of strings and passing them individually"){
    it("should successfully reverse each individual string"){

      val stringList: Seq[String] = Seq("Word1", "Word2", "What's Up", "Backwordz")
      val actorRef = TestActorRef(new StringReverse)
      stringList.foreach( (i: String) => {
        val future = ask(actorRef, ReversibleString(i)).mapTo[String]
        val result = Await.result(future, 1 second)
        result should equal(i.reverse)
      })
    }
  }


}
