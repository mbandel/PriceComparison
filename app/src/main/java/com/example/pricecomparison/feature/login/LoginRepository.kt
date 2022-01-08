package com.example.pricecomparison.feature.login

import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.data.LoginStatus
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun authenticate(credentials: LoginCredentials): Flow<LoginStatus>
}
