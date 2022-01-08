package com.example.pricecomparison.feature.login.data

sealed class LoginStatus {
    object Loading : LoginStatus()
    data class Success(val token: String) : LoginStatus()
    object IncorrectCredentials : LoginStatus()
    object ServerError : LoginStatus()
}
