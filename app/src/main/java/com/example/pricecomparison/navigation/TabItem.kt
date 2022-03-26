package com.example.pricecomparison.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pricecomparison.feature.login.LoginScreen
import com.example.pricecomparison.feature.login.LoginViewModel
import com.example.pricecomparison.feature.register.RegisterScreen
import com.example.pricecomparison.feature.register.RegisterViewModel

sealed class TabItem(
    val title: String,
    val view: @Composable () -> Unit
) {
    object Login : TabItem(
        title = NavigationConst.LOGIN,
        view = { LoginScreen(processor = viewModel<LoginViewModel>().processor) }
    )

    object Register : TabItem(
        title = NavigationConst.REGISTER,
        view = { RegisterScreen(processor = viewModel<RegisterViewModel>().processor) }
    )
}
