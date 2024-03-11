package com.jayesh.jayesh_practical_android_indianic.ui.common_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.jayesh.jayesh_practical_android_indianic.R

@Composable
fun ScreenBackground() {
    Image(
        painter = painterResource(id = R.drawable.weather_bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}