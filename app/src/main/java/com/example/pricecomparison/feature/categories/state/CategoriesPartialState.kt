package com.example.pricecomparison.feature.categories.state

import com.example.pricecomparison.feature.categories.data.Category
import com.tomcz.ellipse.PartialState

sealed interface CategoriesPartialState : PartialState<CategoriesState> {
    data class UpdateCategories(val categories: List<Category>) : CategoriesPartialState {
        override fun reduce(oldState: CategoriesState): CategoriesState =
            oldState.copy(categories = categories)
    }
}