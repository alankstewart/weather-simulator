name := "weather"

version := "1.0"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.scalaj" % "scalaj-http_2.12" % "2.3.0",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.12" % "2.9.2",
  "com.typesafe.scala-logging" % "scala-logging_2.12" % "3.7.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"
)

val cities = Array(
  "Sydney, Australia",
  "Melbourne, Australia",
  "Adelaide, Australia",
  "Brisbane, Australia",
  "Perth, Australia",
  "Hobart, Australia",
  "Canberra, Australia",
  "Darwin, Australia",
  "Ottawa, Canada",
  "New York, USA",
  "Moscow, Russia",
  "London, England",
  "Tokyo, Japan",
  "Montreal, Canada"
)

lazy val weather = taskKey[Unit]("Weather task")
fullRunTask(weather, Runtime, "alankstewart.weather.GenerateWeather", cities: _*)
