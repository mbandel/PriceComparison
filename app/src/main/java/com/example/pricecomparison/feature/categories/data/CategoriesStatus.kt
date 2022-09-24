package com.example.pricecomparison.feature.categories.data

import com.example.pricecomparison.model.Category

sealed interface CategoriesStatus {
    data class Loading(val categories: List<Category>) : CategoriesStatus

    data class Success(val categories: List<Category>) : CategoriesStatus

    object ServerError : CategoriesStatus
}
