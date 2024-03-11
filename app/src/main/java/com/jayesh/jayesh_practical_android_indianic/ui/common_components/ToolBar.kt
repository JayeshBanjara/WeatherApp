package com.jayesh.jayesh_practical_android_indianic.ui.common_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolBar(
    title: String,
    showBack: Boolean = false,
    onBackClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF31363F),
            titleContentColor = Color.White,
        ),
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        title = {
            Text(
                text = title, style = TextStyle(
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.W700
                )
            )
        })
}