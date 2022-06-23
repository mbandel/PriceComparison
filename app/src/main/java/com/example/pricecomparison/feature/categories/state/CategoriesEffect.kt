package com.example.pricecomparison.feature.categories.state

sealed interface CategoriesEffect {
    data class GoToProduct(val categoryId: Int) : CategoriesEffect
}
