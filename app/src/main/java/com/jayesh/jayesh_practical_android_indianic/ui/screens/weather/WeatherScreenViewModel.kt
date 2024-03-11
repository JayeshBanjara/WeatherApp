package com.jayesh.jayesh_practical_android_indianic.ui.screens.weather

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jayesh.jayesh_practical_android_indianic.utility.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var tabIndex = mutableIntStateOf(0)
    val tabs = listOf("Current Weather", "5 Days Forecast")
    var latitude = 0.0
    var longitude = 0.0

    init {
        latitude = savedStateHandle.get<String>(Constants.KEY_LAT)?.toDouble() ?: 0.0
        longitude = savedStateHandle.get<String>(Constants.KEY_LON)?.toDouble() ?: 0.0
    }
}