package com.example.pricecomparison.feature.categories

import com.example.pricecomparison.apiservice.ApiService
import com.example.pricecomparison.feature.categories.data.CategoriesStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CategoriesRepository {
    override fun getCategories(): Flow<CategoriesStatus> = flow {
        try {
            val response = apiService.getCategories()
            if (response.isSuccessful)
                emit(CategoriesStatus.Success(categories = response.body()!!))
        } catch (e: Exception) {
            emit(CategoriesStatus.ServerError)
        }
    }
}
