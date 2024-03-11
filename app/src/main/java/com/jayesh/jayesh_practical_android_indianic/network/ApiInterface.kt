package com.jayesh.jayesh_practical_android_indianic.network

import com.jayesh.jayesh_practical_android_indianic.data.ForecastResult
import com.jayesh.jayesh_practical_android_indianic.data.WeatherResult
import com.jayesh.jayesh_practical_android_indianic.utility.Constants.OPEN_WEATHER_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lon: Double = 0.0,
        @Query("appid") apiKey: String = OPEN_WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): Response<WeatherResult>

    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lon: Double = 0.0,
        @Query("appid") apiKey: String = OPEN_WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): Response<ForecastResult>
}