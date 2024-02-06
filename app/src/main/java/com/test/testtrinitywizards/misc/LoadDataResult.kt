package com.test.testtrinitywizards.misc

sealed class LoadDataResult<T> {
    data class Loading<T>(val message: String = ""): LoadDataResult<T>()
    data class Success<T>(val value: T): LoadDataResult<T>()
    data class Failed<T>(val t: Throwable): LoadDataResult<T>()
}