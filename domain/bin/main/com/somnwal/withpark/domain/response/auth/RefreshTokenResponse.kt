package com.somnwal.withpark.domain.response.auth

import com.somnwal.withpark.domain.model.auth.RefreshToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse (
    @SerialName("accessToken") val accessToken: String,
    @SerialName("refreshToken") val refreshToken: String
)