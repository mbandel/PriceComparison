package com.example.pricecomparison.feature.categories.state

import com.example.pricecomparison.feature.categories.data.Category

data class CategoriesState(
    val categories: List<Category> = listOf(),
    val isConnectionError: Boolean = false
)
