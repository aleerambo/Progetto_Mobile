package com.corsolp.data.repository

import com.corsolp.data.local.TokenManager
import com.corsolp.domain.models.User
import com.corsolp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val tokenManager: TokenManager
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return remoteDataSource.login(email, password)
    }

    override suspend fun register(
        name: String, surname: String, email: String, phone: Long, password: String
    ): Result<User> {
        return remoteDataSource.register(name, surname, email, phone, password)
    }

    override suspend fun logout(): Result<Unit> {
        return remoteDataSource.logout()
    }

    override suspend fun getProfile(): Result<User> {
        return remoteDataSource.getProfile()
    }

    override fun getSavedToken(): String? {
        return tokenManager.getToken()
    }
}
