package com.test.testtrinitywizards.misc.ext

import android.util.Log
import com.test.testtrinitywizards.misc.LoadDataResult
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun<T> getAsyncRequestResult(onGetAsyncRequestResult: suspend () -> T): T {
    return try {
        onGetAsyncRequestResult()
    } catch (e: Exception) {
        throw e
    }
}

suspend fun<T> getAsyncRequestLoadDataResult(
    coroutineContext: CoroutineContext,
    onGetAsyncRequestResult: suspend () -> T
): LoadDataResult<T> {
    return try {
        withContext(coroutineContext) {
            LoadDataResult.Success(onGetAsyncRequestResult())
        }
    } catch (e: Exception) {
        Log.e("Error", e.stackTraceToString())
        LoadDataResult.Failed(e)
    }
}