package com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.current_weather

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.ApiException
import com.jayesh.jayesh_practical_android_indianic.data.WeatherResult
import com.jayesh.jayesh_practical_android_indianic.repository.WeatherRepository
import com.jayesh.jayesh_practical_android_indianic.utility.Constants.KEY_LAT
import com.jayesh.jayesh_practical_android_indianic.utility.Constants.KEY_LON
import com.jayesh.jayesh_practical_android_indianic.utility.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<WeatherResult>>(UiState.Loading)
    val uiState: StateFlow<UiState<WeatherResult>> get() = _uiState

    suspend fun getWeather(latitude: Double, longitude: Double) = viewModelScope.launch {
        try {
            val response = weatherRepository.getWeather(lat = latitude, lon = longitude)
            _uiState.value = UiState.Success(response)
        } catch (e: ApiException) {
            _uiState.value = UiState.Error(e.message)
        } catch (e: Exception) {
            _uiState.value = UiState.Error(e.message)
        }
    }
}