package com.example.pricecomparison.feature.register

import com.example.pricecomparison.feature.register.data.RegisterDTO
import com.example.pricecomparison.feature.register.data.RegisterStatus
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    fun signUp(registerDTO: RegisterDTO): Flow<RegisterStatus>
}