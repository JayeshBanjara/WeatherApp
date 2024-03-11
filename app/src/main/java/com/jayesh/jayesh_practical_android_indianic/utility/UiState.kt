package com.jayesh.jayesh_practical_android_indianic.utility

sealed class UiState<out T> {
    data object Empty : UiState<Nothing>()

    data object Loading : UiState<Nothing>()

    data class Success<out T>(val data: T) : UiState<T>()

    data class Error(val errorMessage: String?) : UiState<Nothing>()
}
