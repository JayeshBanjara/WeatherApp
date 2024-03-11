package com.jayesh.jayesh_practical_android_indianic.ui.screens.map

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayesh.jayesh_practical_android_indianic.ui.screens.map.location.LocationTracker
import com.jayesh.jayesh_practical_android_indianic.utility.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Location>>(UiState.Empty)
    val uiState: StateFlow<UiState<Location>> get() = _uiState

    var currentLocation by mutableStateOf<Location?>(null)

    fun getCurrentLocation() {
        viewModelScope.launch {
            currentLocation = locationTracker.getCurrentLocation()
            if (currentLocation != null) {
                _uiState.value = UiState.Success(currentLocation!!)
                getMarkerAddressDetails()
            }
        }
    }

    private val _markerAddressDetail = MutableStateFlow<UiState<Address>>(UiState.Empty)
    val markerAddressDetail = _markerAddressDetail.asStateFlow()

    private fun getMarkerAddressDetails() {
        _markerAddressDetail.value = UiState.Loading
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocation(
                    currentLocation?.latitude ?: 0.0,
                    currentLocation?.longitude ?: 0.0,
                    1,
                ) { p0 ->
                    _markerAddressDetail.value = UiState.Success(p0[0])
                }
            } else {
                val addresses = geocoder.getFromLocation(
                    currentLocation?.latitude ?: 0.0,
                    currentLocation?.longitude ?: 0.0,
                    1,
                )
                _markerAddressDetail.value =
                    if(!addresses.isNullOrEmpty()){
                        UiState.Success(addresses[0])
                    }else{
                        UiState.Error("Address is null")
                    }
            }
        } catch (e: Exception) {
            _markerAddressDetail.value = UiState.Error(e.message)
        }
    }
}