package com.somnwal.withpark.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.somnwal.withpark.data.BuildConfig
import com.somnwal.withpark.data.datasource.AuthTokenDataSource
import com.somnwal.withpark.data.datasource.TokenDataSource
import com.somnwal.withpark.data.network.util.AuthAuthenticator
import com.somnwal.withpark.data.network.util.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object NetworkModule {
    @Singleton
    @Provides
    @Named("auth")
    fun provideAuthOkHttpClient(
        // 헤더 영역에 인증값을 넣어줄 인터셉터
        interceptor: AuthInterceptor,
        authenticator: AuthAuthenticator
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    @Named("auth")
    fun provideAuthRetrofit(
        @Named("auth") okHttpClient: OkHttpClient
    ): Retrofit {
        val json = Json {
            // 공식 JSON 문법에 맞지 않아도 받아줌
            isLenient = true
            // 예쁘게 표시
            prettyPrint = true
            // 앱에 정의되지 않은 키값은 무시
            ignoreUnknownKeys = true
            // 입력값이 enum에 없거나 타입 불일치 시, 예외 대신 기본값으로 대체할지 여부를 설정
            coerceInputValues = true
        }

        return Retrofit.Builder()
            // TODO BASE_URL 설정
            .baseUrl("")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        // 헤더 영역에 인증값을 넣어줄 인터셉터
        interceptor: AuthInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val json = Json {
            // 공식 JSON 문법에 맞지 않아도 받아줌
            isLenient = true
            // 예쁘게 표시
            prettyPrint = true
            // 앱에 정의되지 않은 키값은 무시
            ignoreUnknownKeys = true
            // 입력값이 enum에 없거나 타입 불일치 시, 예외 대신 기본값으로 대체할지 여부를 설정
            coerceInputValues = true
        }

        return Retrofit.Builder()
            // TODO BASE_URL 설정
            .baseUrl("")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(
        tokenDataSource: TokenDataSource
    ): AuthInterceptor = AuthInterceptor(tokenDataSource)

    @Singleton
    @Provides
    fun provideAuthenticator(
        authTokenDataSource: AuthTokenDataSource
    ): AuthAuthenticator = AuthAuthenticator(authTokenDataSource)
}