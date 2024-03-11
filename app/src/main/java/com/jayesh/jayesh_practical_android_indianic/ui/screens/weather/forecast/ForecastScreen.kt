package com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jayesh.jayesh_practical_android_indianic.ui.VerticalSpacer
import com.jayesh.jayesh_practical_android_indianic.ui.common_components.Loader
import com.jayesh.jayesh_practical_android_indianic.ui.common_components.ScreenBackground
import com.jayesh.jayesh_practical_android_indianic.utility.UiState
import com.jayesh.jayesh_practical_android_indianic.utility.toast

@Composable
fun ForecastScreen(
    viewModel: ForecastViewModel,
    latitude: Double,
    longitude: Double
) {

    LaunchedEffect(Unit) {
        viewModel.getForecast(
            latitude = latitude,
            longitude = longitude
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFF378CE7))
    ) {

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
                val weatherList = (state as UiState.Success).data.weatherList
                LazyColumn(
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item { VerticalSpacer(size = 10) }
                    items(weatherList) {
                        ForecastItem(weatherData = it)
                    }
                }
            }
        }
    }

}