package com.corsolp.domain.usecases

import com.corsolp.domain.models.User
import com.corsolp.domain.repository.AuthRepository

/**
 * Interfaccia: invocabile con (mail, password) → Result<User>
 */
interface LoginUseCase : suspend (String, String) -> Result<User>

/**
 * Implementazione: chiama authRepository.login()
 */
class LoginUseCaseImpl(
    private val authRepository: AuthRepository
) : LoginUseCase {
    override suspend fun invoke(email: String, password: String): Result<User> {
        return authRepository.login(email, password)
    }
}