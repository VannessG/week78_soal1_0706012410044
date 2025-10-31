package com.example.week78_0706012410044.data.container

import com.example.week78_0706012410044.data.service.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherContainer {
    val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    val BASE_ICON_URL = "https://openweathermap.org/img/wn/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val weatherService: WeatherService = retrofit.create(WeatherService::class.java)
}
