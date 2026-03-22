package com.example.weatherapp_android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tietoja sovelluksesta") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Takaisin"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "WeatherApp",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tämä on yksinkertainen sääsovellus, joka on toteutettu käyttäen modernia Android-kehitystä.",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Ominaisuudet:",
                style = MaterialTheme.typography.titleMedium
            )
            Text("- Jetpack Compose käyttöliittymä")
            Text("- MVVM-arkkitehtuuri")
            Text("- Retrofit säätietojen hakuun")
            Text("- Open-Meteo API")
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Versio 1.0",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
