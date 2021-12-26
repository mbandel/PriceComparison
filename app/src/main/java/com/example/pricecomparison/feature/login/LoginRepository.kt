package com.example.pricecomparison.feature.login

import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.data.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun authenticate(credentials: LoginCredentials): Flow<LoginResponse>
}
