package com.example.pricecomparison.feature.categories.data

import com.example.pricecomparison.model.Category

data class CategoryItem(
    val id: Int,
    val name: String
)

fun Category.toCategoryItem(): CategoryItem =
    CategoryItem(id = id, name = name)
