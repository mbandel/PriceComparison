package com.example.pricecomparison.feature.register.state

import com.example.pricecomparison.apiservice.ApiService
import com.example.pricecomparison.feature.register.RegisterRepository
import com.example.pricecomparison.feature.register.data.RegisterDTO
import com.example.pricecomparison.feature.register.data.RegisterStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RegisterRepository {
    override fun signUp(registerDTO: RegisterDTO): Flow<RegisterStatus> = flow {
        emit(RegisterStatus.Loading)
        try {
           val response = apiService.register(registerDTO = registerDTO)
            if (response.isSuccessful) {
                emit(RegisterStatus.Success(response.body()))
            } else {
                emit(RegisterStatus.EmailOrUsernameAlreadyExists)
            }
        } catch (e: Exception) {
            emit(RegisterStatus.ServerError)
        }
    }
}