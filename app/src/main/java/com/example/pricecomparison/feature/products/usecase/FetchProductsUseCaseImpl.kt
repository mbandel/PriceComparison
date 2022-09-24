package com.example.pricecomparison.feature.products.usecase

import com.example.pricecomparison.feature.products.ProductsRepository
import com.example.pricecomparison.feature.products.state.ProductsPartialState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class FetchProductsUseCaseImpl @Inject constructor(
    private val repository: ProductsRepository
) : FetchProductsUseCase {
    override fun invoke(
        categoryId: Int,
        coroutineScope: CoroutineScope
    ): Flow<ProductsPartialState> =
        repository.observeProducts(categoryId).map { products ->
            ProductsPartialState.UpdateProducts(products = products)
        }
            .onStart {
                coroutineScope.launch {
                    val products = repository.getProducts()
                    println(products)
                    repository.saveProducts(products)
                }
            }
}
