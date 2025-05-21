package com.somnwal.withpark.domain.response.auth

import com.somnwal.withpark.domain.model.auth.LoginUserState
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("isOnboardingDone") val isOnboardingDone: Boolean = false,
    @SerialName("accessToken") val accessToken: String?,
    @SerialName("refreshToken") val refreshToken: String?,
) {
    fun toDomain(): LoginUserState = LoginUserState(
        isOnboardingDone = isOnboardingDone
    )
}