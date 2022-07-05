package com.example.pricecomparison.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pricecomparison.feature.categories.CategoriesScreen
import com.example.pricecomparison.ui.compose.TabsLayout
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreen.LoginTabs.route
    ) {
        composable(route = MainScreen.LoginTabs.route) {
            TabsLayout()
        }
        composable(route = MainScreen.Categories.route) {
            CategoriesScreen()
        }
    }
}
