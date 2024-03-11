package com.jayesh.jayesh_practical_android_indianic.repository

import com.jayesh.jayesh_practical_android_indianic.data.ForecastResult
import com.jayesh.jayesh_practical_android_indianic.data.WeatherResult
import com.jayesh.jayesh_practical_android_indianic.network.ApiInterface
import com.jayesh.jayesh_practical_android_indianic.network.SafeApiRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val apiInterface: ApiInterface) :
    SafeApiRequest() {

    suspend fun getWeather(lat: Double, lon: Double): WeatherResult {
        return apiRequest { apiInterface.getWeather(lat = lat, lon = lon) }
    }

    suspend fun getForecast(lat: Double, lon: Double): ForecastResult {
        return apiRequest { apiInterface.getForecast(lat = lat, lon = lon) }
    }
}