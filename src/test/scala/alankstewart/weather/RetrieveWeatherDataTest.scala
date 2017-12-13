package alankstewart.weather

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{FlatSpec, Matchers}

class RetrieveWeatherDataTest extends FlatSpec with Matchers with LazyLogging {

  private val weatherData = RetrieveWeatherData()

  "RetrieveWeatherData" should "retrieve weather data for one city only" in {
    val weather = weatherData.getCurrentWeather("Sydney, Australia")
    weather should have size 1
  }

  it should "retrieve weather data for multiple cities" in {
    val weather = weatherData.getCurrentWeather("Sydney, Australia", "Perth, Australia", "Chicago, USA", "Ottawa, Canada")
    weather.foreach(c => logger.debug(c.toString))
    weather should have size 4
  }

  it should "handle non-existent city" in {
    val weather = weatherData.getCurrentWeather("Sydney, Australia", "Perth, Australia", "Com Foo, Foo Bar")
    weather should have size 2
  }
}
