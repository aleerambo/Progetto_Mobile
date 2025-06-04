package com.corsolp.domain.usecases

import com.corsolp.domain.repository.RentalRepository

/**
 * Interfaccia: invocabile come “(id: Int) → Result<Unit>”
 */
interface ActivateRentalPostUseCase : suspend (Int) -> Result<Unit>

/**
 * Implementazione: chiama rentalRepository.activateRentalPost()
 */
class ActivateRentalPostUseCaseImpl(
    private val rentalRepository: RentalRepository
) : ActivateRentalPostUseCase {
    override suspend fun invoke(id: Int): Result<Unit> {
        return rentalRepository.activateRentalPost(id)
    }
}