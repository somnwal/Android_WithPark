package com.somnwal.withpark.domain.repository

import com.somnwal.withpark.domain.model.auth.AccessToken
import com.somnwal.withpark.domain.model.auth.LoginUserState
import com.somnwal.withpark.domain.model.auth.TokenPairs
import com.somnwal.withpark.domain.model.auth.User
import com.somnwal.withpark.domain.request.auth.LoginRequest
import kotlinx.coroutines.flow.Flow

/**
 * 사용자 인증 관련 레포지토리
 */
interface AuthRepository {
    val loggedIn: Flow<Boolean>
    val cachedUser: Flow<User.Info?>

    // 로그인 관련 함수
    suspend fun login(request: LoginRequest): Result<LoginUserState>
    suspend fun kakaoLogin(request: LoginRequest): Result<LoginUserState>
    suspend fun logout(): Result<Unit>

    // 토큰 관리 함수
    fun getTokens(): Flow<TokenPairs>
    fun refreshToken(): Flow<AccessToken>

}