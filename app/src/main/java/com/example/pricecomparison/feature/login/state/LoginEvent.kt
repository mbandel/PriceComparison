package com.example.pricecomparison.feature.login.state

sealed interface LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    data class LoginClick(val email: String, val password: String) : LoginEvent
    object ConfirmServerError : LoginEvent
    object ConfirmInvalidCredentialsError : LoginEvent
}
