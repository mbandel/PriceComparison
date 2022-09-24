package com.example.pricecomparison.feature.products

import com.example.pricecomparison.apiservice.ApiService
import com.example.pricecomparison.database.CategoriesDao
import com.example.pricecomparison.database.ProductsDao
import com.example.pricecomparison.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val productsDao: ProductsDao,
    private val categoryDao: CategoriesDao
) : ProductsRepository {

    override fun observeProducts(categoryId: Int): Flow<List<Product>> {
        val productCategory = categoryDao.getCategoryById(categoryId)
        return productsDao.observeProducts(category = productCategory)
    }

    override suspend fun saveProducts(products: List<Product>) {
        productsDao.insertProducts(products = products)
    }

    override suspend fun getProducts(): List<Product> {
        val response = apiService.getAllProducts()
        return response.body().orEmpty()
    }
}
