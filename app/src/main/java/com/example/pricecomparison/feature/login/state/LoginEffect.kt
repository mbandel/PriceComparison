package com.example.pricecomparison.feature.login.state

sealed interface LoginEffect {
    object GoToCategories : LoginEffect
    data class ShowError(val error: String) : LoginEffect
}
