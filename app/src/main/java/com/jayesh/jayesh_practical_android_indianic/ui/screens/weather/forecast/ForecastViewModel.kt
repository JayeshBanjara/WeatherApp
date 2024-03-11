package com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.ApiException
import com.jayesh.jayesh_practical_android_indianic.data.ForecastResult
import com.jayesh.jayesh_practical_android_indianic.data.WeatherResult
import com.jayesh.jayesh_practical_android_indianic.repository.WeatherRepository
import com.jayesh.jayesh_practical_android_indianic.utility.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<ForecastResult>>(UiState.Loading)
    val uiState: StateFlow<UiState<ForecastResult>> get() = _uiState

    suspend fun getForecast(latitude: Double, longitude: Double) = viewModelScope.launch {
        try {
            val response = weatherRepository.getForecast(lat = latitude, lon = longitude)
            _uiState.value = UiState.Success(response)
        } catch (e: ApiException) {
            _uiState.value = UiState.Error(e.message)
        } catch (e: Exception) {
            _uiState.value = UiState.Error(e.message)
        }
    }
}