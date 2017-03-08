name := """GeneralLearning"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % "2.3.11"
                           ,"com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test"
                           ,"com.typesafe.akka" %% "akka-agent" % "2.3.11")

//Keeps Exception: sbt.TrapExitSecurityException thrown from the UncaughtExceptionHandler in thread "run-main-0"
//from occuring when calling sbt run
trapExit := false
