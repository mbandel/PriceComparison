package com.example.pricecomparison.feature.login

import com.example.pricecomparison.apiservice.ApiService
import com.example.pricecomparison.datastore.DataStorage
import com.example.pricecomparison.feature.login.data.LoginCredentials
import com.example.pricecomparison.feature.login.data.LoginStatus
import com.example.pricecomparison.util.Constants.AUTH_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataStorage: DataStorage
) : LoginRepository {
    override fun authenticate(
        credentials: LoginCredentials
    ): Flow<LoginStatus> = flow {
        emit(LoginStatus.Loading)
        try {
            val response = apiService.authenticate(credentials)
            if (response.isSuccessful) {
                response.body()?.token?.let {
                    println("authToken: $it")
                    dataStorage.save(AUTH_TOKEN, it)
                    emit(LoginStatus.Success(it))
                }
            } else {
                emit(LoginStatus.IncorrectCredentials)
                println("incorrect credentials")
            }
        } catch (e: Exception) {
            println("Login Exception ${e.message}")
            emit(LoginStatus.ServerError)
        }
    }
}
