package com.example.pricecomparison.feature.categories

import com.example.pricecomparison.feature.categories.data.CategoriesStatus
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {

    fun getCategories(): Flow<CategoriesStatus>
}
