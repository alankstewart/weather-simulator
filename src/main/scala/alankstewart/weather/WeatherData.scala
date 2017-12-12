package alankstewart.weather

case class WeatherData(query: Query)

case class Query(count: Int, results: Results)

case class Results(channel: Seq[Channel])

case class Channel(title: String, location: Location, atmosphere: Atmosphere, item: Item)

case class Location(city: String, country: String, region: String)

case class Atmosphere(humidity: String, pressure: String)

case class Item(lat: String, long: String, condition: Condition)

case class Condition(code: String, date: String, temp: String, text: String)
