package alankstewart.weather

import java.time.format.DateTimeFormatter
import java.util.Locale

import alankstewart.weather.JsonUtils.fromJson

import scalaj.http.Http

object RetrieveWeatherData {

  private val HomeUri = "https://query.yahooapis.com/v1/public/yql"

  def apply(): RetrieveWeatherData = new RetrieveWeatherData
}

class RetrieveWeatherData {

  import RetrieveWeatherData._

  def getCurrentWeather(cities: String*): Seq[String] = {
    val qry = s"select * from weather.forecast where u = 'c' and woeid in (select woeid from geo.places(1) where text in (${cities.mkString("'", "', '", "'")}))"
    val weather = Http(HomeUri)
      .params(Map("q" -> qry, "format" -> "json"))
      .execute(fromJson[WeatherData])
      .throwError
      .body

    weather.query.results.channel
      .map(c =>
        s"${c.location.city}|${c.item.lat}|${c.item.long}|${localTime(c.item.condition.date)}|${c.item.condition.temp}|${c.atmosphere.pressure}|${c.atmosphere.humidity}")
  }

  private def localTime(ts: String) = {
    val date = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mm a z", Locale.ENGLISH).parse(ts)
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(date)
  }
}
