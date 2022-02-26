package com.example.pricecomparison.feature.register.state

sealed interface RegisterEvent {
    data class RegisterClick(
        val firstName: String,
        val lastName: String,
        val username: String,
        val email: String,
        val password: String
    ) : RegisterEvent

    data class FirstNameChanged(val firstName: String) : RegisterEvent

    data class LastNameChanged(val lastName: String) : RegisterEvent

    data class UsernameChanged(val username: String) : RegisterEvent

    data class EmailChanged(val email: String) : RegisterEvent

    data class PasswordChanged(val password: String) : RegisterEvent

    data class RepeatPasswordChanged(val repeatPassword: String) : RegisterEvent
}
