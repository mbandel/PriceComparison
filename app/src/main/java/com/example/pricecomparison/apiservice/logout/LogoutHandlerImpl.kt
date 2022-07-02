package com.example.pricecomparison.apiservice.logout

import com.example.pricecomparison.datastore.DataStorage
import com.example.pricecomparison.util.Constants.AUTH_TOKEN
import javax.inject.Inject

class LogoutHandlerImpl @Inject constructor(
    private val dataStorage: DataStorage
) : LogoutHandler {
    override suspend fun logout() = dataStorage.save(AUTH_TOKEN, "")
}
