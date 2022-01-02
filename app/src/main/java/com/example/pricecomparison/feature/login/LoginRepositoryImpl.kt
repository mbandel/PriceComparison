package com.example.pricecomparison.feature.login

import com.example.pricecomparison.apiservice.ApiService
import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.data.LoginStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LoginRepository {
    override suspend fun authenticate(credentials: LoginCredentials): Flow<LoginStatus> = flow {
        emit(LoginStatus.Loading)
        try {
            val response = apiService.authenticate(credentials)
            if (response.isSuccessful) {
                emit(LoginStatus.Success(response.body()!!))
                println("LOGIN_SUCCESS")
            } else {
                emit(LoginStatus.IncorrectCredentials)
                println("LOGIN_INVALID_CRED")
            }
        } catch (e: SocketTimeoutException) {
            emit(LoginStatus.ServerError)
            println(e.toString())
        }
    }
}
