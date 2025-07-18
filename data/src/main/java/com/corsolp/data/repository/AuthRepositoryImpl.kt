package com.corsolp.data.repository

import com.corsolp.data.local.TokenManager
import com.corsolp.data.remote.AuthApi
import com.corsolp.data.remote.models.auth.AuthResponse
import com.corsolp.data.remote.models.auth.GetAuthProfile
import com.corsolp.domain.models.User
import com.corsolp.domain.repository.AuthRepository
import com.corsolp.data.remote.models.auth.LoginRequest
import com.corsolp.data.remote.models.auth.RegisterRequest
import retrofit2.HttpException

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
            // Ottengo AuthResponse
            val authResponse: AuthResponse = authApi.login(LoginRequest(email, password))

            // Salvo token
            authResponse.token?.let { tokenManager.saveToken(it) }

            // Creo modello User
            val user = User(
                authResponse.user?.id ?: 0,
                authResponse.user?.mail ?: "",
                authResponse.user?.ruolo ?: ""
            )
            Result.success(user)

        } catch (e: HttpException) {
            // errore HTTP (4xx/5xx)
            Result.failure(Exception("Login fallito: ${e.code()} ${e.message}"))
        } catch (e: Exception) {
            // rete, parsing, ecc.
            Result.failure(e)
        }
    }

    override suspend fun register(
        name: String,
        surname: String,
        phone: String,
        email: String,
        password: String
    ): Result<User> {
        return try {
            println("AuthRepositoryImpl: Sending registration request for: $email")
            val registerResponse: AuthResponse = authApi.register(RegisterRequest(name, surname, phone, email, password))

            // Salvo il token se la registrazione è andata a buon fine
            registerResponse.token?.let { tokenManager.saveToken(it) }

            // Creo modello User
            val user = User(
                registerResponse.user?.id ?: 0,
                registerResponse.user?.mail ?: "",
                registerResponse.user?.ruolo ?: ""
            )
            Result.success(user)

        } catch (e: HttpException) {
            Result.failure(Exception("Registrazione fallita: ${e.code()} ${e.message()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            // Se anche postLogout non restituisce nulla di utile, lo chiamiamo e
            // non ci interessa il risultato: se lancia, intercettiamo, altrimenti ok
            authApi.postLogout()
            tokenManager.clearToken()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProfile(): Result<User> {
        return try {
            val profileResponse: GetAuthProfile = authApi.getAuthProfile()

            // Creo modello User
            val user = User(
                profileResponse.id ?: 0,
                profileResponse.mail ?: "",
                profileResponse.ruolo ?: ""
            )
            Result.success(user)

        } catch (e: HttpException) {
            Result.failure(Exception("Recupero profilo fallito: ${e.code()} ${e.message()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getSavedToken(): String? {
        return tokenManager.getToken()
    }
}
