# weather-simulator

[![Build Status](https://travis-ci.org/alankstewart/weather-simulator.svg?branch=master)](https://travis-ci.org/alankstewart/weather-simulator)

Simulates weather and outputs weather data in a standard format.

Retrieves current weather data from the Yahoo Weather API.

To run:

```
 sbt weather

```

A text file, `weather.txt`, is written with current weather conditions for a number of cities

Known issues:

* By specifying `u = 'c'` for metric units, Yahoo does not return the atmospheric pressure in hPa as documented
* Elevation for a city is not returned by the Yahoo Weather API

