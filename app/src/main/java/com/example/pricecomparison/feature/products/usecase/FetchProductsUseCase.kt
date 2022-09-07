package com.example.pricecomparison.feature.products.usecase

import com.example.pricecomparison.feature.products.state.ProductsPartialState
import kotlinx.coroutines.flow.Flow

interface FetchProductsUseCase {
    operator fun invoke(categoryId: Int): Flow<ProductsPartialState>
}