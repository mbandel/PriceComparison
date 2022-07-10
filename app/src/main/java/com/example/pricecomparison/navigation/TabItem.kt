package com.example.pricecomparison.navigation

import androidx.compose.runtime.Composable
import com.example.pricecomparison.feature.login.LoginScreen
import com.example.pricecomparison.feature.register.RegisterScreen

sealed class TabItem(
    val title: String,
    val view: @Composable () -> Unit
) {
    object Login : TabItem(
        title = NavigationConst.LOGIN,
        view = { LoginScreen() }
    )

    object Register : TabItem(
        title = NavigationConst.REGISTER,
        view = { RegisterScreen() }
    )
}
