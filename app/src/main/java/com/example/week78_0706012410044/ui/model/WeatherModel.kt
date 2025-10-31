package com.example.week78_0706012410044.ui.model

data class WeatherModel(
    val city: String = "",
    val temperature: Int = 0,
    val humidity: Int = 0,
    val windSpeed: Int = 0,
    val feelsLike: Int = 0,
    val rainfall: Double = 0.0,
    val pressure: Int = 0,
    val clouds: Int = 0,
    val iconUrl: String = "",
    val condition: String = "",
    val sunrise: Long = 0,
    val sunset: Long = 0
)