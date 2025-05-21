package com.somnwal.withpark.data.network.util

import com.somnwal.withpark.data.datasource.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class AuthInterceptor @Inject constructor(
    private val tokenDataSource: TokenDataSource
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // runBlocking : 실행 결과를 기다림
        val accessToken = runBlocking { tokenDataSource.getAccessToken() }

        // 통신 헤더에 Authorization 항목 추가
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        )

    }

}