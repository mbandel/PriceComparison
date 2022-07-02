package com.example.pricecomparison.datastore

import com.example.pricecomparison.feature.login.data.UserAuth
import kotlinx.coroutines.flow.Flow

interface DataStorage {
    suspend fun save(key: String, value: String)
    suspend fun read(key: String): String
    fun observeIfUserLoggedIn(): Flow<UserAuth>
}
