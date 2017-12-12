package alankstewart.weather

import java.io.InputStream
import java.text.SimpleDateFormat

import com.fasterxml.jackson.databind.DeserializationFeature.{ACCEPT_SINGLE_VALUE_AS_ARRAY, ADJUST_DATES_TO_CONTEXT_TIME_ZONE, FAIL_ON_UNKNOWN_PROPERTIES}
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

object JsonUtils {

  private val Mapper = new ObjectMapper() with ScalaObjectMapper
  Mapper.registerModule(DefaultScalaModule)
    .disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE, FAIL_ON_UNKNOWN_PROPERTIES)
    .enable(ACCEPT_SINGLE_VALUE_AS_ARRAY)
    .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX"))

  def fromJson[T](is: InputStream)(implicit m: Manifest[T]): T = Mapper.readValue[T](is)

  def toJson(value: Any): String = Mapper.writeValueAsString(value)
}
