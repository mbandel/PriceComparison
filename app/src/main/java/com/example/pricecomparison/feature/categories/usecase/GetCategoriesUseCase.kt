package com.example.pricecomparison.feature.categories.usecase

import com.example.pricecomparison.feature.categories.state.CategoriesPartialState
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUseCase {
    operator fun invoke(): Flow<CategoriesPartialState>
}
