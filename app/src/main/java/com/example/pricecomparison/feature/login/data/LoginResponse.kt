package com.example.pricecomparison.feature.login.data

sealed class LoginResponse {
    object Loading : LoginResponse()
    object Success : LoginResponse()
    object InvalidCredentials : LoginResponse()
    object ServerError : LoginResponse()
}
