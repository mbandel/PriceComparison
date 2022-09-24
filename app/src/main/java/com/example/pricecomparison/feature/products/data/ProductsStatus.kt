package com.example.pricecomparison.feature.products.data

import com.example.pricecomparison.model.Product

sealed class ProductsStatus {

    data class Loading(val products: List<Product>) : ProductsStatus()

    data class Success(val products: List<Product>) : ProductsStatus()

    object ServerError
}
