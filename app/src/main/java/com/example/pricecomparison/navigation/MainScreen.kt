package com.example.pricecomparison.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.pricecomparison.feature.categories.CategoriesScreen
import com.example.pricecomparison.feature.products.ProductsScreen
import com.example.pricecomparison.ui.compose.TabsLayout
import com.google.accompanist.pager.ExperimentalPagerApi

object TabsLayout : Screen {
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    override fun Content() {
        TabsLayout()
    }
}

object CategoriesScreen : Screen {
    @Composable
    override fun Content() {
        CategoriesScreen()
    }
}

data class ProductsScreen(val categoryId: Int) : Screen {
    @Composable
    override fun Content() {
        ProductsScreen(categoryId = categoryId)
    }
}
