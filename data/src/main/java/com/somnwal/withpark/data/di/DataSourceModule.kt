package com.somnwal.withpark.data.di

import android.content.Context
import com.somnwal.withpark.data.datasource.AuthDataSource
import com.somnwal.withpark.data.datasource.AuthTokenDataSource
import com.somnwal.withpark.data.datasource.TokenDataSource
import com.somnwal.withpark.data.network.api.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object DataSourceModule {
    @Singleton
    @Provides
    fun provideAuthDataSource(
        @ApplicationContext context: Context,
        authService: AuthService
    ): AuthDataSource = AuthDataSource(context, authService)

    @Singleton
    @Provides
    fun provideTokenDataSource(
        @ApplicationContext context: Context
    ): TokenDataSource = TokenDataSource(context)

    @Singleton
    @Provides
    fun provideAuthTokenDataSource(
        authDataSource: AuthDataSource,
        tokenDataSource: TokenDataSource
    ): AuthTokenDataSource = AuthTokenDataSource(authDataSource, tokenDataSource)
}