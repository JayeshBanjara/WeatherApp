package com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.current_weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jayesh.jayesh_practical_android_indianic.ui.VerticalSpacer
import com.jayesh.jayesh_practical_android_indianic.ui.common_components.Loader
import com.jayesh.jayesh_practical_android_indianic.ui.common_components.ScreenBackground
import com.jayesh.jayesh_practical_android_indianic.utility.UiState
import com.jayesh.jayesh_practical_android_indianic.utility.formatDate
import com.jayesh.jayesh_practical_android_indianic.utility.formatTime
import com.jayesh.jayesh_practical_android_indianic.utility.toast

@Composable
fun CurrentWeatherScreen(
    viewModel: CurrentWeatherViewModel,
    latitude: Double,
    longitude: Double
) {

    LaunchedEffect(Unit) {
        viewModel.getWeather(
            latitude = latitude,
            longitude = longitude
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ScreenBackground()

        val context = LocalContext.current
        val state by remember { viewModel.uiState }.collectAsStateWithLifecycle()
        when (state) {
            is UiState.Empty -> {}
            is UiState.Error -> {
                val errorMessage = (state as UiState.Error).errorMessage
                context.toast(message = errorMessage)
            }

            is UiState.Loading -> {
                Loader()
            }

            is UiState.Success -> {
                val weatherData = (state as UiState.Success).data

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = weatherData.main?.temp.toString() + " °C",
                            style = TextStyle(
                                fontSize = 45.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        VerticalSpacer(size = 30)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Sunrise: ${weatherData.sys?.sunrise.toString().formatTime()}")
                            Text(text = "Sunset: ${weatherData.sys?.sunset.toString().formatTime()}")
                        }
                        VerticalSpacer(size = 20)
                        Text(text = "Wind Speed: ${weatherData.wind?.speed}")
                    }
                }
            }
        }
    }

}