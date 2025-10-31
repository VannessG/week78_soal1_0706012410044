package com.example.week78_0706012410044.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week78_0706012410044.data.container.WeatherContainer
import com.example.week78_0706012410044.data.repository.WeatherRepository
import com.example.week78_0706012410044.ui.model.WeatherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class WeatherState(
    val data: WeatherModel? = null,
    val isError: Boolean = false
)

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository(WeatherContainer.weatherService)
    private val _weatherState = MutableStateFlow(WeatherState())
    val weatherState: StateFlow<WeatherState> = _weatherState
    fun showWeather(city: String) {
        viewModelScope.launch {
            val data = repository.getWeather(city)
            if (data != null) {
                _weatherState.value = WeatherState(data = data)
            } else {
                _weatherState.value = WeatherState(isError = true)
            }
        }
    }
}