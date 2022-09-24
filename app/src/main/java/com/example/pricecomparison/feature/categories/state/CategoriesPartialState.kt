package com.example.pricecomparison.feature.categories.state

import com.example.pricecomparison.feature.categories.data.CategoryItem
import com.tomcz.ellipse.PartialState

sealed interface CategoriesPartialState : PartialState<CategoriesState> {
    data class UpdateCategories(val categories: List<CategoryItem>) : CategoriesPartialState {
        override fun reduce(oldState: CategoriesState): CategoriesState =
            oldState.copy(categories = categories)
    }

    object ShowConnectionError : CategoriesPartialState {
        override fun reduce(oldState: CategoriesState): CategoriesState =
            oldState.copy(isConnectionError = true)
    }

    object HideConnectionError : CategoriesPartialState {
        override fun reduce(oldState: CategoriesState): CategoriesState =
            oldState.copy(isConnectionError = false)
    }
}
