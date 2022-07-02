package com.example.pricecomparison.feature.register.state

sealed interface RegisterEffect {
    object GoToCategories : RegisterEffect
    object ShowEmailOrUsernameExists : RegisterEffect
}
