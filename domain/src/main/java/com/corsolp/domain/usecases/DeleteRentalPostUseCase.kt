package com.corsolp.domain.usecases

import com.corsolp.domain.repository.RentalRepository

/**
 * Interfaccia: invocabile come “(id: Int) → Result<Unit>”
 */
interface DeleteRentalPostUseCase : suspend (Int) -> Result<Unit>

/**
 * Implementazione: chiama rentalRepository.deleteRentalPost()
 */
class DeleteRentalPostUseCaseImpl(
    private val rentalRepository: RentalRepository
) : DeleteRentalPostUseCase {
    override suspend fun invoke(id: Int): Result<Unit> {
        return rentalRepository.deleteRentalPost(id)
    }
}