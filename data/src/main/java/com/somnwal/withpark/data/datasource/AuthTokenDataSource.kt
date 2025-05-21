package com.somnwal.withpark.data.datasource

import javax.inject.Inject

// 인증 토큰 관리하는 데이터 소스
internal class AuthTokenDataSource @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataSource: TokenDataSource
) {
    suspend fun getNewAccessToken(): String? {
        val response = authDataSource.refreshToken()

        // 새로운 토큰을 받아왔다.
        val newToken = response.getOrNull()

        return newToken?.let {
            // 새로 받아온 토큰을 저장한다.
            tokenDataSource.saveTokens(
                accessToken = it.accessToken,
                refreshToken = it.refreshToken
            )

            it.accessToken
        // 토큰을 받아왔는데 어찌됐건 없으면 로그아웃 시킨다.
        } ?: run {
            authDataSource.logout()
            null
        }
    }
}