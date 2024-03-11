package com.jayesh.jayesh_practical_android_indianic.ui.screens.map.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}