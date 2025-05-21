package com.somnwal.withpark.domain.model.auth

sealed interface User {
    val nickname: String
    val photo: String?
    val userCode: String
    val introduction: String

    data class Info(
        val id: String,
        override val nickname: String = "",
        override val photo: String? = "",
        override val userCode: String = "",
        override val introduction: String = "",
    ): User
}