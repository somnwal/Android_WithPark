package com.somnwal.withpark.data.network.api

import com.somnwal.withpark.domain.request.auth.LoginRequest
import com.somnwal.withpark.domain.request.auth.RefreshTokenRequest
import com.somnwal.withpark.domain.response.auth.LoginResponse
import com.somnwal.withpark.domain.response.auth.RefreshTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthService {
    @POST("/api/v1/auth/login/kakao")
    suspend fun kakaoLogin(@Body request: LoginRequest): LoginResponse

    @POST("/api/vi/auth/logout")
    suspend fun logout()

    @POST("/app/papi/v1/auth/refresh")
    suspend fun refreshToken(@Body refreshToken: RefreshTokenRequest): RefreshTokenResponse
}