name := "akkadb"
organization := "com.akkadb"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.2"

lazy val akkaVersion = "2.5.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.typesafe.akka" %% "akka-remote" % akkaVersion
)

// Exclude application.conf from publishing
excludeFilter in unmanagedSources := HiddenFileFilter || "*.conf"

// Publish Locally
publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))