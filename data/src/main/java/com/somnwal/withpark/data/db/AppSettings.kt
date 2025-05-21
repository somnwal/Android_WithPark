package com.somnwal.withpark.data.db

import androidx.datastore.core.Serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Serializable
internal data class AppSettings(
    val userId: String? = null,
    val loginType: String? = null,
    val nickname: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val photo: String? = null,
    val userCode: String? = null,
    val profileIntroduction: String = "",

    val accessToken: String = "",
    val refreshToken: String = "",
)

internal object AppSettingsSerializer: Serializer<AppSettings> {
    // 기본으로 제공될 값을 정의
    override val defaultValue: AppSettings = AppSettings()

    // 어디서 읽어올 것인가
    override suspend fun readFrom(input: InputStream): AppSettings {
        return try {
            // JSON으로 저장된 앱 셋팅 값들을 읽어온다.
            Json.decodeFromString(
                deserializer = AppSettings.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()

            // 오류나면 기본값 반환
            defaultValue
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: AppSettings, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AppSettings.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }

}