package com.example.pricecomparison.navigation

sealed class MainScreen(val route: String) {
    object LoginTabs : MainScreen("login_tabs_screen")
    object Categories : TabsScreen("categories_screen")
}
