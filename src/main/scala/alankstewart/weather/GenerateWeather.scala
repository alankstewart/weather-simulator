package alankstewart.weather

import java.io.{File, PrintWriter}

object GenerateWeather extends App {

  val weather = RetrieveWeatherData().getCurrentWeather("Sydney,Australia", "Melbourne,Australia",
    "Brisbane,Australia", "Adelaide,Australia", "Perth,Australia", "Ottawa,Canada")

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
