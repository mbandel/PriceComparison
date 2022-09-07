package com.example.pricecomparison.feature.products.usecase

import com.example.pricecomparison.apiservice.ApiService
import com.example.pricecomparison.feature.products.state.ProductsPartialState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FetchProductsUseCaseImpl @Inject constructor(
    private val apiService: ApiService
) : FetchProductsUseCase {
    override operator fun invoke(categoryId: Int): Flow<ProductsPartialState> = flow {
        val products = apiService.getProductsByCategoryId(id = categoryId)
        if (products.isSuccessful) {
            emit(ProductsPartialState.UpdateProducts(products = products.body()!!))
        }
    }
}