package com.corsolp.domain.usecases

import com.corsolp.domain.repository.AuthRepository

/**
 * Interfaccia: invocabile come “() → Result<Unit>”
 */
interface LogoutUseCase : suspend () -> Result<Unit>

/**
 * Implementazione: chiama authRepository.logout()
 */
class LogoutUseCaseImpl(
    private val authRepository: AuthRepository
) : LogoutUseCase {
    override suspend fun invoke(): Result<Unit> {
        return authRepository.logout()
    }
}
