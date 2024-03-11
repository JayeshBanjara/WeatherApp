package com.jayesh.jayesh_practical_android_indianic.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(
    @Json(name = "sunrise")
    val sunrise: Int?,
    @Json(name = "sunset")
    val sunset: Int?
)