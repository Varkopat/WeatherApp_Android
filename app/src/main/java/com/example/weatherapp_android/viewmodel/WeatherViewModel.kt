package com.example.weatherapp_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp_android.model.WeatherResponse
import com.example.weatherapp_android.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val weather: WeatherResponse) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                val result = repository.getWeather(lat, lon)
                _uiState.value = WeatherUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}
