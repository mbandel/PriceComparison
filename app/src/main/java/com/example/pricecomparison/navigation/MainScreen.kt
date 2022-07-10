package com.example.pricecomparison.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.pricecomparison.feature.categories.CategoriesScreen
import com.example.pricecomparison.feature.login.LoginScreen
import com.example.pricecomparison.feature.register.RegisterScreen
import com.example.pricecomparison.ui.compose.TabsLayout
import com.google.accompanist.pager.ExperimentalPagerApi

sealed class MainScreen(val route: String) {
    object LoginTabs : MainScreen("login_tabs_screen")
    object Categories : MainScreen("categories_screen")
}

object TabsLayout : Screen {
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    override fun Content() {
        TabsLayout()
    }
}

object LoginScreen : Screen {
    @Composable
    override fun Content() { LoginScreen() }
}

object RegisterScreen : Screen {
    @Composable
    override fun Content() { RegisterScreen() }
}

object CategoriesScreen : Screen {
    @Composable
    override fun Content() { CategoriesScreen() }
}
