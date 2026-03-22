package com.example.weatherapp_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp_android.network.WeatherApiService
import com.example.weatherapp_android.repository.WeatherRepository
import com.example.weatherapp_android.ui.AboutScreen
import com.example.weatherapp_android.ui.HomeScreen
import com.example.weatherapp_android.ui.WeatherScreen
import com.example.weatherapp_android.ui.theme.WeatherApp_AndroidTheme
import com.example.weatherapp_android.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApp_AndroidTheme {
                WeatherApp()
            }
        }
    }
}

@Composable
fun WeatherApp() {
    val navController = rememberNavController()
    
    // Yksinkertainen tapa luoda ViewModel ilman DI-kirjastoa
    val apiService = remember { WeatherApiService.create() }
    val repository = remember { WeatherRepository(apiService) }
    
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToWeather = { lat, lon ->
                    navController.navigate("weather/$lat/$lon")
                },
                onNavigateToAbout = {
                    navController.navigate("about")
                }
            )
        }
        composable("about") {
            AboutScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "weather/{lat}/{lon}",
            arguments = listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lon") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val lat = backStackEntry.arguments?.getFloat("lat")?.toDouble() ?: 0.0
            val lon = backStackEntry.arguments?.getFloat("lon")?.toDouble() ?: 0.0
            
            val viewModel: WeatherViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return WeatherViewModel(repository) as T
                    }
                }
            )
            
            WeatherScreen(
                lat = lat,
                lon = lon,
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
