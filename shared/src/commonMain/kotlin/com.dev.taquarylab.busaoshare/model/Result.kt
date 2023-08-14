package com.dev.taquarylab.busaoshare.model

sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val e: Exception) : Result<Nothing>()
}