package com.example.weatherapp_android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp_android.viewmodel.WeatherUiState
import com.example.weatherapp_android.viewmodel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    lat: Double,
    lon: Double,
    viewModel: WeatherViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(lat, lon) {
        viewModel.fetchWeather(lat, lon)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Säätiedot") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Takaisin")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is WeatherUiState.Loading -> CircularProgressIndicator()
                is WeatherUiState.Success -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${state.weather.currentWeather.temperature}°C",
                            style = MaterialTheme.typography.displayLarge
                        )
                        Text(
                            text = "Tuulen nopeus: ${state.weather.currentWeather.windspeed} km/h",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Aika: ${state.weather.currentWeather.time}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                is WeatherUiState.Error -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Virhe: ${state.message}", color = MaterialTheme.colorScheme.error)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.fetchWeather(lat, lon) }) {
                            Text("Yritä uudelleen")
                        }
                    }
                }
            }
        }
    }
}
