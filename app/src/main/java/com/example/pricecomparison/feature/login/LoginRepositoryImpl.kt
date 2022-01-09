package com.example.pricecomparison.feature.login

import com.example.pricecomparison.apiservice.ApiService
import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.data.LoginStatus
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LoginRepository {
    override fun authenticate(
        credentials: LoginCredentials
    ) = flow {
        emit(LoginStatus.Loading)
        try {
            val response = apiService.authenticate(credentials)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(LoginStatus.Success(it.token))
                    println("Success")
                }
            } else {
                emit(LoginStatus.IncorrectCredentials)
                println("incorrect credentials")
            }
        } catch (e: IOException) {
            emit(LoginStatus.ServerError)
        }
    }
}
