package alankstewart.weather

import org.scalatest.{FlatSpec, Matchers}

class RetrieveWeatherDataTest extends FlatSpec with Matchers {

  "RetrieveWeatherData" should "retrieve sample weather data for one city only" in {
    val weather = RetrieveWeatherData().getCurrentWeather("Sydney, Australia")
    weather should have size 1
  }

  it should "retrieve sample weather data for multiple cities" in {
    val weather = RetrieveWeatherData().getCurrentWeather("Sydney, Australia", "Perth, Australia", "Chicago, USA")
    weather should have size 3
  }

  it should "handle non-existent city" in {
    val weather = RetrieveWeatherData().getCurrentWeather("Sydney, Australia", "Perth, Australia", "Com Foo, Foo Bar")
    weather should have size 2
  }
}
