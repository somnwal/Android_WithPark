package com.somnwal.withpark.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import com.somnwal.withpark.data.db.AppSettings
import com.somnwal.withpark.data.db.dataStore
import com.somnwal.withpark.domain.model.auth.RefreshToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

// 순수 토큰만을 관리하는 데이터 소스
internal class TokenDataSource @Inject constructor(
    private val context: Context
) {
    private val dataStore: DataStore<AppSettings>
        get() = context.dataStore

    private val data: Flow<AppSettings>
        get() = dataStore.data

    suspend fun getAccessToken(): String = data.first().accessToken

    suspend fun getTokenPair(): Pair<String, String> =
        data.first().run { Pair(accessToken, refreshToken) }

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        dataStore.updateData {
            it.copy(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }
}