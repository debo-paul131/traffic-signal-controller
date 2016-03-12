name := "traffic-signal-controller"

version := "1.0"

mainClass := Some("Application")

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

libraryDependencies ++= Seq(
		 "com.typesafe.akka" %% "akka-actor" % "2.2-M3",
  		 "com.typesafe.akka" % "akka-slf4j_2.10" % "2.2-M3",
 		 "ch.qos.logback" % "logback-classic" % "1.1.2",
 		 "com.typesafe.akka" % "akka-testkit_2.10" % "2.2-M3" % "test",
 		 "org.scalatest" %% "scalatest" % "2.2.1" % "test",
		"com.github.nscala-time" %% "nscala-time" % "0.6.0",
	   	("pl.project13.scala" %% "rainbow" % "0.2").exclude("org.scalatest","scalatest_2.11"),
		"org.slf4j" % "slf4j-api" % "1.7.5",
                "org.slf4j" % "slf4j-simple" % "1.7.5")
