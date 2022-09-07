package com.example.pricecomparison.apiservice

import com.example.pricecomparison.datastore.DataStorage
import com.example.pricecomparison.util.Constants.APPLICATION_JSON
import com.example.pricecomparison.util.Constants.AUTHORIZATION
import com.example.pricecomparison.util.Constants.AUTH_TOKEN
import com.example.pricecomparison.util.Constants.BEARER
import com.example.pricecomparison.util.Constants.CONTENT_TYPE
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeaderInterceptor @Inject constructor(
    private val dataStorage: DataStorage
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        try {
            when (val authToken = dataStorage.read(key = AUTH_TOKEN)) {
                "" -> chain.run {
                    println("interceptor, empty token")
                    proceed(
                        request().newBuilder().build()
                    )
                }
                else -> {
                    println("interceptor, authToken: $authToken")
                    chain.run {
                        proceed(
                            request()
                                .newBuilder()
                                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                                .addHeader(AUTHORIZATION, "$BEARER $authToken")
                                .build()
                        )
                    }
                }
            }
        } catch (e: Exception) {
            println("interceptor exception ${e.message}")
            chain.run {
                proceed(
                    request().newBuilder().build()
                )
            }
        }
    }
}
