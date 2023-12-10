package com.example.simpleyandexbulb

sealed class UiState<out T> {
    class Success<T>(val value: T) : UiState<T>()
    class Failure(val message: String) : UiState<Nothing>()
    class NotAvailable(val message: String) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}

fun <T> Result<T>.toUiState(): UiState<T> {
    return if (this.isSuccess) {
        val result = this.getOrNull()
        result?.let { UiState.Success(it) }
            ?: UiState.NotAvailable("Not available, turn on the bulb")
    } else this.exceptionOrNull()?.let { UiState.Failure(it.message ?: "Unknown error") }
        ?: UiState.Failure("Unknown error")
}
