package com.example.pricecomparison.feature.login

import android.util.Log
import com.example.pricecomparison.apiservice.ApiService
import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.data.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LoginRepository {
    override suspend fun authenticate(credentials: LoginCredentials): Flow<LoginResponse> = flow {
        emit(LoginResponse.Loading)
        val request = apiService.authenticate(credentials)
        try {
            if (request.isSuccessful) {
                emit(LoginResponse.Success)
            } else {
                emit(LoginResponse.InvalidCredentials)
            }
        } catch (e: Exception) {
            emit(LoginResponse.ServerError)
            Log.d("LOGIN_TAG", e.toString())
        }
    }
}
