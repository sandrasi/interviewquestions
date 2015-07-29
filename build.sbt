name := "interview-questions"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.7"

javacOptions ++= Seq("-encoding", "UTF-8")

scalacOptions += "-Xexperimental"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % Test,
  "org.scalacheck" %% "scalacheck" % "1.12.4" % Test
)

autoAPIMappings := true // This requires the m:properties__info.apiURL property to be defined for each dependency in their Ivy XML

