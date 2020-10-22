name := "akka-test"

version := "0.1"

scalaVersion := "2.11.12"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.3"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-testkit
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.3.6" % Test

// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.6"% Test

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-remote
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.3.6"





