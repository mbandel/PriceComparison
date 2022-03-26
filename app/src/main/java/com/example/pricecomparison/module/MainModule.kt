package com.example.pricecomparison.module

import com.example.pricecomparison.feature.login.LoginRepository
import com.example.pricecomparison.feature.login.LoginRepositoryImpl
import com.example.pricecomparison.feature.register.RegisterRepository
import com.example.pricecomparison.feature.register.RegisterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MainModule {
    @Binds
    fun provideLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    fun provideRegisterRepository(
        registerRepositoryImpl: RegisterRepositoryImpl
    ): RegisterRepository
}
