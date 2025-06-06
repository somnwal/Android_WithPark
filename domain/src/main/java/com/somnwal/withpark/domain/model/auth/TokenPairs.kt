package com.somnwal.withpark.domain.model.auth

data class TokenPairs(
    val accessToken: String,
    val refreshToken: String,
) {
    val isLoggedIn: Boolean = accessToken.isNotBlank() && refreshToken.isNotBlank()
}

@JvmInline
value class AccessToken(val token: String)

@JvmInline
value class RefreshToken(val token: String)