package com.example.pricecomparison.feature.login.data

sealed class UserAuth {
    object LoggedIn : UserAuth()
    object LoggedOut : UserAuth()
}
