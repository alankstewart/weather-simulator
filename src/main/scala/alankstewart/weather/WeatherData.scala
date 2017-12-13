package alankstewart.weather

import java.time.format.DateTimeFormatter
import java.util.Locale

case class WeatherData(query: Query)

case class Query(count: Int, results: Results)

case class Results(channel: Seq[CurrentWeather])

case class CurrentWeather(title: String, location: Location, atmosphere: Atmosphere, item: Item) {

  override def toString =
    s"${location.city}|${item.lat}|${item.long}|${localTime(item.condition.date)}|${conditions(item.condition.code)}|${item.condition.temp}|${atmosphere.pressure}|${atmosphere.humidity}"

  private def localTime(ts: String) = {
    val date = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mm a z", Locale.ENGLISH).parse(ts)
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(date)
  }

  /**
    * Yahoo condition codes - see https://developer.yahoo.com/weather/documentation.html#codes
    *
    * @param code the condition code
    * @return a condition as a String
    */
  private def conditions(code: String): String = code.toInt match {
    case 0 | 1 | 2 | 3 | 4 | 8 | 9 | 10 | 11 | 12 | 17 | 37 | 38 | 39 | 40 | 45 | 47 => "Rain"
    case 5 | 6 | 7 | 13 | 14 | 15 | 16 | 18 | 41 | 42 | 43 => "Snow"
    case _ => "Sunny"
  }
}

case class Location(city: String, country: String, region: String)

case class Atmosphere(humidity: String, pressure: String)

case class Item(lat: String, long: String, condition: Condition)

case class Condition(code: String, date: String, temp: String, text: String)
