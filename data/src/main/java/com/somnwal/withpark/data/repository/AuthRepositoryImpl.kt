package com.somnwal.withpark.data.repository

import com.somnwal.withpark.data.datasource.AuthDataSource
import com.somnwal.withpark.domain.model.auth.AccessToken
import com.somnwal.withpark.domain.model.auth.LoginUserState
import com.somnwal.withpark.domain.model.auth.TokenPairs
import com.somnwal.withpark.domain.model.auth.User
import com.somnwal.withpark.domain.repository.AuthRepository
import com.somnwal.withpark.domain.request.auth.LoginRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override val loggedIn: Flow<Boolean>
        get() = authDataSource.loggedIn

    override val cachedUser: Flow<User.Info?>
        get() = TODO("Not yet implemented")

    override suspend fun login(request: LoginRequest): Result<LoginUserState> {
        TODO("Not yet implemented")
    }

    override suspend fun kakaoLogin(request: LoginRequest): Result<LoginUserState> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun getTokens(): Flow<TokenPairs> {
        TODO("Not yet implemented")
    }

    override fun refreshToken(): Flow<AccessToken> {
        TODO("Not yet implemented")
    }

}