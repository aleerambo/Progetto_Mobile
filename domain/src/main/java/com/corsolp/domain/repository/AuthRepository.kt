package com.corsolp.domain.repository

import com.corsolp.domain.models.User

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String
    ): Result<User>

    suspend fun register(
        name: String,
        surname: String,
        email: String,
        phone: String,
        password: String
    ): Result<User>

    suspend fun logout(): Result<Unit>

    suspend fun getProfile(): Result<User>

    fun getSavedToken(): String?
}
