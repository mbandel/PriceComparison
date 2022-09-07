package com.example.pricecomparison.apiservice

import com.example.pricecomparison.apiservice.ApiConst.AUTHENTICATE_URL
import com.example.pricecomparison.apiservice.ApiConst.CATEGORIES_URL
import com.example.pricecomparison.apiservice.ApiConst.PRODUCTS_BY_CATEGORY_URL
import com.example.pricecomparison.apiservice.ApiConst.REGISTER_URL
import com.example.pricecomparison.feature.categories.data.Category
import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.data.LoginResponse
import com.example.pricecomparison.feature.register.data.RegisterDTO
import com.example.pricecomparison.feature.register.data.RegisterResponse
import com.example.pricecomparison.model.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST(AUTHENTICATE_URL)
    suspend fun authenticate(@Body loginCredentials: LoginCredentials): Response<LoginResponse>

    @POST(REGISTER_URL)
    suspend fun register(@Body registerDTO: RegisterDTO): Response<RegisterResponse>

    @GET(CATEGORIES_URL)
    suspend fun getCategories(): Response<List<Category>>

    @GET("$PRODUCTS_BY_CATEGORY_URL{id}")
    suspend fun getProductsByCategoryId(@Path("id") id: Int): Response<List<Product>>
}
