package com.example.pricecomparison.feature.products

import com.example.pricecomparison.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun observeProducts(categoryId: Int): Flow<List<Product>>

    suspend fun saveProducts(products: List<Product>)

    suspend fun getProducts(): List<Product>
}
