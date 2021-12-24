package com.example.pricecomparison.feature.login.state

sealed interface LoginEvent {
    data class EmailChanged(val email: String)
    data class PasswordChanged(val password: String)
    data class LoginClick(val email: String, val password: String)
}