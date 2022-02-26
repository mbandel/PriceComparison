package com.example.pricecomparison.feature.register.data

sealed class RegisterStatus {
    object Loading : RegisterStatus()
    data class Success(val response: RegisterResponse?) : RegisterStatus()
    object EmailOrUsernameAlreadyExists : RegisterStatus()
    object ServerError : RegisterStatus()
}
