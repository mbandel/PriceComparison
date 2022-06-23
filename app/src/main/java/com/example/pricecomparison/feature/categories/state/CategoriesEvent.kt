package com.example.pricecomparison.feature.categories.state

sealed interface CategoriesEvent {
    data class OnItemClick(val categoryId: Int) : CategoriesEvent
}
