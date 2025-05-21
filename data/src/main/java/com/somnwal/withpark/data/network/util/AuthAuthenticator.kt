package com.somnwal.withpark.data.network.util

import com.somnwal.withpark.data.datasource.AuthTokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

internal class AuthAuthenticator @Inject constructor(
    private val authTokenDataSource: AuthTokenDataSource
): Authenticator {
    // 인증된 사용자인지 검증 어떻게 할것인가?
    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken = runBlocking { authTokenDataSource.getNewAccessToken() } ?: return null

        // 엑세스 토큰이 아직 유효하다면... 헤더에 토큰 설정
        return response.request.newBuilder()
            .header("Authentication", "Bearer $accessToken")
            .build()
    }

}