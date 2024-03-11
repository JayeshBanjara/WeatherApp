package com.jayesh.jayesh_practical_android_indianic.utility

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Context.toast(message: String?) {
    Toast.makeText(this, message ?: "Something went wrong", Toast.LENGTH_SHORT).show()
}

fun String.formatDate(): String {

    val outputFormat = SimpleDateFormat("dd MMM - hh:mm a", Locale.ENGLISH)

    val date = Date(this.toLong())

    return outputFormat.format(date)
}

fun String.formatTime(): String {

    val outputPattern = "hh:mm a"
    val outputFormat = SimpleDateFormat(outputPattern, Locale.ENGLISH)

    val date = Date(this.toLong())

    return outputFormat.format(date)
}