package com.example.pricecomparison.apiservice

import com.example.pricecomparison.apiservice.ApiConst.AUTHENTICATE_URL
import com.example.pricecomparison.apiservice.ApiConst.REGISTER_URL
import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.data.LoginResponse
import com.example.pricecomparison.feature.register.data.RegisterDTO
import com.example.pricecomparison.feature.register.data.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(AUTHENTICATE_URL)
    suspend fun authenticate(
        @Body loginCredentials: LoginCredentials
    ): Response<LoginResponse>

    @POST(REGISTER_URL)
    suspend fun register(
        @Body registerDTO: RegisterDTO
    ): Response<RegisterResponse>
}
