package com.somnwal.withpark.data.di

import com.somnwal.withpark.data.network.api.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class ServiceModule {
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): AuthService = retrofit.create()

    @Singleton
    @Provides
    @Named("auth")
    fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create()
}