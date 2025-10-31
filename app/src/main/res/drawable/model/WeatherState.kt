// ui/state/WeatherState.kt
package com.example.week78_0706012410044.ui.state

import com.example.week78_0706012410044.data.dto.ResponseWeather

sealed class WeatherState {
    object Empty : WeatherState()
    object Loading : WeatherState()
    data class Success(val data: ResponseWeather) : WeatherState()
    data class Error(val message: String) : WeatherState()
}