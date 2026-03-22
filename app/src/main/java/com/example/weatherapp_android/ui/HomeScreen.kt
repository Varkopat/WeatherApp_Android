package com.example.weatherapp_android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToWeather: (Double, Double) -> Unit,
    onNavigateToAbout: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sääsovellus", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = { onNavigateToWeather(60.1695, 24.9354) }, // Helsinki
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Helsingin sää")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { onNavigateToWeather(61.4978, 23.7610) }, // Tampere
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tampereen sää")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigateToWeather(60.4516, 22.2668) }, // Turku
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Turun sää")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigateToWeather(65.0137, 25.4720) }, // Oulu
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Oulun sää")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigateToWeather(62.6020, 29.7596) }, // Joensuu
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Joensuun sää")
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(
            onClick = onNavigateToAbout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tietoja sovelluksesta")
        }
    }
}
