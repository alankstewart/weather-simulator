package alankstewart.weather

import java.io.{File, PrintWriter}

object GenerateWeather extends App {

  private val cities = args.length match {
    case 0 => Array("Sydney,Australia")
    case _ => args
  }

  val weather = RetrieveWeatherData().getCurrentWeather(cities)
  weather.foreach(println)
  printToFile(new File("weather.txt")) {
    p => weather.foreach(p.println)
  }

  def printToFile(f: File)(op: PrintWriter => Unit) {
    val p = new PrintWriter(f)
    try {
      op(p)
    } finally {
      p.close()
    }
  }
}
