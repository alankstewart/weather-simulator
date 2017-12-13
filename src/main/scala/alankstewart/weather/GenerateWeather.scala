package alankstewart.weather

import java.io.{File, PrintWriter}

import com.typesafe.scalalogging.LazyLogging

object GenerateWeather extends App with LazyLogging {

  private val cities = args.length match {
    case 0 => Array("Sydney, Australia")
    case _ => args
  }

  val weather = RetrieveWeatherData().getCurrentWeather(cities)
  weather.foreach(c => logger.info(c.toString))
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
