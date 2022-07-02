package com.example.pricecomparison.module

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.pricecomparison.datastore.DataStorage
import com.example.pricecomparison.datastore.DataStorageImpl
import com.example.pricecomparison.feature.categories.CategoriesRepository
import com.example.pricecomparison.feature.categories.CategoriesRepositoryImpl
import com.example.pricecomparison.feature.login.LoginRepository
import com.example.pricecomparison.feature.login.LoginRepositoryImpl
import com.example.pricecomparison.feature.login.LoginUseCase
import com.example.pricecomparison.feature.login.LoginUseCaseImpl
import com.example.pricecomparison.feature.register.RegisterRepository
import com.example.pricecomparison.feature.register.RegisterRepositoryImpl
import com.example.pricecomparison.feature.register.usecase.RegisterUseCase
import com.example.pricecomparison.feature.register.usecase.RegisterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MainModule {
    @Binds
    fun bindDataStorage(dataStorageImpl: DataStorageImpl): DataStorage

    @Binds
    fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    fun bindLoginUseCase(
        loginUseCaseImpl: LoginUseCaseImpl
    ): LoginUseCase

    @Binds
    fun bindRegisterRepository(
        registerRepositoryImpl: RegisterRepositoryImpl
    ): RegisterRepository

    @Binds
    fun bindRegisterUseCase(
        registerUseCaseImpl: RegisterUseCaseImpl
    ): RegisterUseCase

    @Binds
    fun bindCategoriesRepository(
        categoriesRepositoryImpl: CategoriesRepositoryImpl
    ): CategoriesRepository
}

@Module
@InstallIn(SingletonComponent::class)
object ProvidesModule {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "Application Data Store"
    )

    @Provides
    @Singleton
    fun provideDataStore(context: Application): DataStore<Preferences> {
        return context.dataStore
    }
}
