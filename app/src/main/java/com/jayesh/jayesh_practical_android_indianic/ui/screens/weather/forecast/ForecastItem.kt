package com.jayesh.jayesh_practical_android_indianic.ui.screens.weather.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jayesh.jayesh_practical_android_indianic.data.WeatherResult
import com.jayesh.jayesh_practical_android_indianic.utility.formatDate

@Composable
fun ForecastItem(weatherData: WeatherResult) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Cyan
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = weatherData.dt.toString().formatDate(),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )
            Text(
                text = weatherData.main?.temp.toString() + " °C",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Black
                )
            )
        }
    }

}