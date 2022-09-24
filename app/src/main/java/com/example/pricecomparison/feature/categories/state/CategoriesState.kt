package com.example.pricecomparison.feature.categories.state

import com.example.pricecomparison.feature.categories.data.CategoryItem

data class CategoriesState(
    val categories: List<CategoryItem> = listOf(),
    val isConnectionError: Boolean = false
)
