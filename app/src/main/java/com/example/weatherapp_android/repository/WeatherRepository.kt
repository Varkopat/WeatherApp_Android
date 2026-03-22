package com.example.weatherapp_android.repository

import com.example.weatherapp_android.model.WeatherResponse
import com.example.weatherapp_android.network.WeatherApiService

class WeatherRepository(private val apiService: WeatherApiService) {
    suspend fun getWeather(lat: Double, lon: Double): WeatherResponse {
        return apiService.getWeather(lat, lon)
    }
}
