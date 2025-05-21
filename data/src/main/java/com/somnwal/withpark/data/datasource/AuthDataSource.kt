package com.somnwal.withpark.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import com.somnwal.withpark.data.db.AppSettings
import com.somnwal.withpark.data.db.dataStore
import com.somnwal.withpark.data.network.api.AuthService
import com.somnwal.withpark.data.type.auth.LoginType
import com.somnwal.withpark.domain.request.auth.LoginRequest
import com.somnwal.withpark.domain.request.auth.RefreshTokenRequest
import com.somnwal.withpark.domain.response.auth.RefreshTokenResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AuthDataSource @Inject constructor(
    private val context: Context,
    private val authService: AuthService
) {
    // 앱 환경설정 값 정의
    private val dataStore: DataStore<AppSettings>
        get() = context.dataStore

    private val data: Flow<AppSettings>
        get() = dataStore.data

    // 사용자 정의
    val user: Flow<String?>
        get() {
            return dataStore.data.map {
                if(it.userId == null) {
                    // 사용자 ID가 비어있으면 null 반환
                    null
                } else {
                    // TODO 사용자 정보 반환
                    null
                }
            }
        }

    val loggedIn: Flow<Boolean>
        get() = data.map { it.accessToken.isNotBlank() }

    suspend fun login(loginType: LoginType,request: LoginRequest) = runCatching {
        when(loginType) {
            LoginType.Kakao -> authService.kakaoLogin(request)
        }
    }.onSuccess {

    }

    suspend fun logout(): Result<Unit> = runCatching {
        localLogout()
        authService.logout()
    }

    suspend fun localLogout() {
        // 내부 데이터를 닦아준다.
        dataStore.updateData {
            it.copy(
                userId = null,
                loginType = null,
                nickname = null,
                email = null,
                phoneNumber = null,
                photo = null,
                userCode = null,
                profileIntroduction = "",
                accessToken = "",
                refreshToken = "",
            )
        }
    }

    suspend fun refreshToken(): Result<RefreshTokenResponse?> = runCatching {
        val refreshToken = data.map { it.refreshToken }.first()

        if (refreshToken.isNotBlank()) {
            authService.refreshToken(RefreshTokenRequest(refreshToken))
        } else {
            null
        }
    }
}