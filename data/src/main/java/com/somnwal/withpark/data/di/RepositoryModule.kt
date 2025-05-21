package com.somnwal.withpark.data.di

import com.somnwal.withpark.data.repository.AuthRepositoryImpl
import com.somnwal.withpark.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository
}