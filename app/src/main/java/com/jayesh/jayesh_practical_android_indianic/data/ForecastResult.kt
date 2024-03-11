package com.jayesh.jayesh_practical_android_indianic.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResult(
    @Json(name = "cod")
    val cod: Int?,
    @Json(name = "message")
    val message: Int?,
    @Json(name = "list")
    val weatherList: List<WeatherResult>
)