package com.example.week78_0706012410044.data.repository

import com.example.week78_0706012410044.data.container.WeatherContainer
import com.example.week78_0706012410044.data.service.WeatherService
import com.example.week78_0706012410044.ui.model.WeatherModel

class WeatherRepository(
    private val service: WeatherService
) {
    private val apiKey = "52883315a97c687e67a5bcc822eed637"
    suspend fun getWeather(city: String): WeatherModel? {
        val response = service.getWeather(city = city, apiKey = apiKey)
        if (response.isSuccessful && response.body() != null) {
            val data = response.body()!!
            return WeatherModel(
                city = data.name,
                temperature = data.main.temp.toInt(),
                humidity = data.main.humidity,
                windSpeed = data.wind.speed.toInt(),
                feelsLike = data.main.feels_like.toInt(),
                rainfall = data.rain?.`1h` ?: 0.0,
                pressure = data.main.pressure,
                clouds = data.clouds.all,
                iconUrl = "${WeatherContainer.BASE_ICON_URL}${data.weather.firstOrNull()?.icon}@2x.png",
                condition = data.weather.firstOrNull()?.main ?: "",
                sunrise = data.sys.sunrise.toLong(),
                sunset = data.sys.sunset.toLong()
            )
        }
        return null
    }
}