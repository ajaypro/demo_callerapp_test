package com.source.truecallerapp.data

sealed class Result {

    data class Success(val data: String) : Result()

    data class ConnectionError(val error: String) : Result()

    data class ServerError(val error: String) : Result()

    data class Error(val exception: Throwable) : Result()

    data class UnExpectedError(val code: Int) : Result()

    object NetworkError : Result() {

        fun castToNetworkError(code: Int) =
            when (code) {
                500 -> ServerError("Server is not responding")
                408 -> ConnectionError("Connection timeout Error")
                else -> UnExpectedError(code)
            }
    }
}