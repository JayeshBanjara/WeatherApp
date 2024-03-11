package com.jayesh.jayesh_practical_android_indianic.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "speed")
    val speed: Double?
)