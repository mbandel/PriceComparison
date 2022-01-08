package com.example.pricecomparison.feature.register.state

data class RegisterState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)
