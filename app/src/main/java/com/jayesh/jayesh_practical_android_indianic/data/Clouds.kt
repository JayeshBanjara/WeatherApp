package com.jayesh.jayesh_practical_android_indianic.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Clouds(
    @Json(name = "all")
    val all: Int?
)