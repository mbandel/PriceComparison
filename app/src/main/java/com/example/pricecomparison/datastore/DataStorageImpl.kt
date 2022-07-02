package com.example.pricecomparison.datastore

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.pricecomparison.feature.login.data.UserAuth
import com.example.pricecomparison.util.Constants.AUTH_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorageImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStorage {
    override suspend fun save(key: String, value: String) {
        dataStore.edit { dataStore ->
            dataStore[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun read(key: String): String =
        dataStore.data.map { dataStore ->
            dataStore[stringPreferencesKey(key)] ?: ""
        }
            .first()

    override fun observeIfUserLoggedIn(): Flow<UserAuth> =
        dataStore.data.map { dataStore ->
            when (dataStore[stringPreferencesKey(AUTH_TOKEN)]) {
                "" -> UserAuth.LoggedOut
                else -> UserAuth.LoggedIn
            }
        }
}
