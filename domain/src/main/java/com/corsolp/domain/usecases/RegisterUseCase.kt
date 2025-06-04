package com.corsolp.domain.usecases

import com.corsolp.domain.models.User
import com.corsolp.domain.repository.AuthRepository

/**
 * Interfaccia: invocabile con (nome, cognome, mail, telefono, password) → Result<User>
 */
interface RegisterUseCase :
    suspend (
        String,
        String,
        String,
        String,
        String,
    ) -> Result<User>

/**
 * Implementazione: chiama authRepository.register()
 */
class RegisterUseCaseImpl(
    private val authRepository: AuthRepository
) : RegisterUseCase {
    override suspend fun invoke(
        name: String,
        surname: String,
        email: String,
        phone: String,
        password: String
    ): Result<User> {
        return authRepository.register(name, surname, email, phone, password)
    }
}
