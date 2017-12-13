name := "weather"

version := "1.0"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.scalaj" % "scalaj-http_2.12" % "2.3.0",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.12" % "2.9.2",
  "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"
)

mainClass in(Compile, run) := Some("alankstewart.weather.GenerateWeather")
