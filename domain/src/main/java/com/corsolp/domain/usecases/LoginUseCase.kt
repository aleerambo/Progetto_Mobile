package com.corsolp.domain.usecases

import com.corsolp.domain.models.User
import com.corsolp.domain.repository.AuthRepository
import javax.inject.Inject

interface LoginUseCase {
    suspend operator fun invoke(mail: String, password: String): Result<User>
}

class LoginUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
): LoginUseCase {
    override suspend operator fun invoke(mail: String, password: String): Result<User> {
        return authRepository.login(mail, password)
    }
}
