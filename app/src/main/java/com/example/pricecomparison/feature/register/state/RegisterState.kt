package com.example.pricecomparison.feature.register.state

data class RegisterState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isFirstNameCorrect: Boolean = true,
    val isLastNameCorrect: Boolean = true,
    val isEmailCorrect: Boolean = true,
    val isUsernameCorrect: Boolean = true,
    val isPasswordCorrect: Boolean = true,
    val isRepeatPasswordCorrect: Boolean = true,
    val isShowingServerError: Boolean = false,
    val isLoading: Boolean = false
)
