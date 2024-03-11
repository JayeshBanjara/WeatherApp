package com.jayesh.jayesh_practical_android_indianic.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jayesh.jayesh_practical_android_indianic.ui.screens.SplashScreen
import com.jayesh.jayesh_practical_android_indianic.ui.screens.map.MapScreen
import com.jayesh.jayesh_practical_android_indianic.ui.screens.map.MapViewModel
import com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.WeatherScreen
import com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.WeatherScreenViewModel
import com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.current_weather.CurrentWeatherScreen
import com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.current_weather.CurrentWeatherViewModel
import com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.forecast.ForecastScreen
import com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.forecast.ForecastViewModel
import com.jayesh.jayesh_practical_android_indianic.utility.Constants.KEY_LAT
import com.jayesh.jayesh_practical_android_indianic.utility.Constants.KEY_LON

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screens.MapScreen.route) {
            val viewModel = hiltViewModel<MapViewModel>()
            MapScreen(navController = navController, viewModel)
        }

        composable(route = Screens.WeatherScreen.route + "/{${KEY_LAT}}/{${KEY_LON}}") {
            val viewModel = hiltViewModel<WeatherScreenViewModel>()
            WeatherScreen(navController = navController, viewModel)
        }
    }

}