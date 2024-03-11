package com.jayesh.jayesh_practical_android_indianic.ui.navigation

sealed class Screens(val route: String) {
    data object SplashScreen: Screens("splash_screen")
    data object MapScreen: Screens("map_screen")
    data object WeatherScreen: Screens("weather_screen")
    data object CurrentWeatherScreen: Screens("current_weather_screen")
    data object ForecastScreen: Screens("forecast_screen")

    /**
     * Use this function to pass arguments to navigation destination
     */
    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
