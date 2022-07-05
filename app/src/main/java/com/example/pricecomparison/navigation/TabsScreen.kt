package com.example.pricecomparison.navigation

sealed class TabsScreen(val route: String) {
    object LoginScreen : TabsScreen("login_screen")
    object RegisterScreen : TabsScreen("register_screen")
}
