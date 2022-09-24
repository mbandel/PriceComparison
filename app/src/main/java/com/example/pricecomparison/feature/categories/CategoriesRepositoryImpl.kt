package com.example.pricecomparison.feature.categories

import com.example.pricecomparison.apiservice.ApiService
import com.example.pricecomparison.database.CategoriesDao
import com.example.pricecomparison.feature.categories.data.CategoriesStatus
import com.example.pricecomparison.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val categoriesDao: CategoriesDao
) : CategoriesRepository {
    override fun getCategories(): Flow<CategoriesStatus> = flow {
        try {
            emit(
                CategoriesStatus.Loading(
                    categories = categoriesDao.getAllCategories()
                )
            )
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                val categories = response.body().orEmpty()
                saveCategories(categories = categories)
                emit(CategoriesStatus.Success(categories = categories))
                println("categoeries success: ${response.body()}")
            }
        } catch (e: Exception) {
            println("Categories error ${e.message}")
            emit(CategoriesStatus.ServerError)
        }
    }

    private fun saveCategories(categories: List<Category>) {
        categoriesDao.saveAllCategories(categories = categories)
    }
}
