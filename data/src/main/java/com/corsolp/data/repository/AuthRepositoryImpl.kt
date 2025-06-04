package com.corsolp.data.repository

import com.corsolp.data.local.TokenManager
import com.corsolp.data.remote.AuthApi
import com.corsolp.domain.models.User
import com.corsolp.domain.repository.AuthRepository
import com.corsolp.data.remote.models.auth.LoginRequest
import com.corsolp.data.remote.models.auth.RegisterRequest

/**
 * Questa implementazione unisce:
 *  - le chiamate a AuthApi (Retrofit)
 *  - la logica di salvataggio/lettura del token via TokenManager
 *  - la conversione da UserDto → User (modello di dominio)
 */
class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = authApi.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                val body = response.body()!!
                // Salva token
                tokenManager.saveToken(body.token)
                // Crea modello di dominio User
                val user = User(body.user.id, body.user.mail, body.user.ruolo)
                Result.success(user)
            } else {
                Result.failure(Exception("Login fallito: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(
        name: String, surname: String, email: String, phone: String, password: String
    ): Result<User> {
        return try {
            val response = authApi.register(RegisterRequest(name, surname, email, phone, password))
            if (response.isSuccessful) {
                val body = response.body()!!
                tokenManager.saveToken(body.token)
                val user = User(body.user.id, body.user.mail, body.user.ruolo)
                Result.success(user)
            } else {
                Result.failure(Exception("Registrazione fallita: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            authApi.postLogout()
            tokenManager.clearToken()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProfile(): Result<User> {
        return try {
            val response = authApi.getAuthProfile()
            if (response.isSuccessful) {
                val dto = response.body()!!
                val user = User(dto.id, dto.mail, dto.ruolo)
                Result.success(user)
            } else {
                Result.failure(Exception("getProfile fallito: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getSavedToken(): String? {
        return tokenManager.getToken()
    }
}
