package com.example.pricecomparison.feature.products.state

import com.example.pricecomparison.model.Product

data class ProductsState(
    val products: List<Product> = listOf()
)
