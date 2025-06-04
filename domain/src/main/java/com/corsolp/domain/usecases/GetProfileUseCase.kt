package com.corsolp.domain.usecases

import com.corsolp.domain.models.User
import com.corsolp.domain.repository.AuthRepository

/**
 * Interfaccia: invocabile come “() → Result<User>”
 */
interface GetProfileUseCase : suspend () -> Result<User>

/**
 * Implementazione: chiama authRepository.getProfile()
 */
class GetProfileUseCaseImpl(
    private val authRepository: AuthRepository
) : GetProfileUseCase {
    override suspend fun invoke(): Result<User> {
        return authRepository.getProfile()
    }
}