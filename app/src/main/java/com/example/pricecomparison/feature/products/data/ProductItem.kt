package com.example.pricecomparison.feature.products.data

import com.example.pricecomparison.model.Product

data class ProductItem(
    val id: Int,
    val name: String,
    val categoryName: String
)

fun Product.toProductItem(): ProductItem =
    ProductItem(
        id = id,
        name = name,
        categoryName = category.name
    )
