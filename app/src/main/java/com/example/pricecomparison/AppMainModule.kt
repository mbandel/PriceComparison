package com.example.pricecomparison

import com.example.pricecomparison.feature.login.LoginRepository
import com.example.pricecomparison.feature.login.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppMainModule {
    @Binds
    fun provideLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository
}
