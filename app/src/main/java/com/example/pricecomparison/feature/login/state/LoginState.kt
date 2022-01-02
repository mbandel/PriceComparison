package com.example.pricecomparison.feature.login.state

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isEmailCorrect: Boolean = true,
    val isPasswordCorrect: Boolean = true,
    val isLoading: Boolean = false,
    val isShowingServerError: Boolean = false,
    val isShowingInvalidCredentialsError: Boolean = false
)
