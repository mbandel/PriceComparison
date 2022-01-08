package com.example.pricecomparison.feature.register.state

sealed interface RegisterEvent {
    data class RegisterClick(
        val firstName: String,
        val lastName: String,
        val username: String,
        val email: String,
        val password: String
    ) : RegisterEvent
}
