package alankstewart.weather

import alankstewart.weather.JsonUtils.fromJson

import scalaj.http.Http

object RetrieveWeatherData {

  private val HomeUri = "https://query.yahooapis.com/v1/public/yql"

  def apply(): RetrieveWeatherData = new RetrieveWeatherData
}

/**
  * Provides weather data from Yahoo.
  */
class RetrieveWeatherData {

  import RetrieveWeatherData._

  def getCurrentWeather(cities: String*) = {
    val qry = s"select * from weather.forecast where u = 'c' and woeid in (select woeid from geo.places(1) where text in (${cities.mkString("'", "', '", "'")}))"
    val weatherData = Http(HomeUri)
      .params(Map("q" -> qry, "format" -> "json"))
      .execute(fromJson[WeatherData])
      .throwError
      .body

    weatherData.query.results.channel
  }

  def getCurrentWeather(cities: Array[String]): Seq[CurrentWeather] = {
    getCurrentWeather(cities: _*)
  }
}