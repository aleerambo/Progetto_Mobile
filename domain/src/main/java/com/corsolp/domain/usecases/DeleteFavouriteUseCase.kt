package com.corsolp.domain.usecases

import com.corsolp.domain.repository.RentalRepository

/**
 * Interfaccia: invocabile come “(id: Int) → Result<Unit>”
 */
interface DeleteFavouriteUseCase : suspend (Int) -> Result<Unit>

/**
 * Implementazione: chiama rentalRepository.deleteFavourite()
 */
class DeleteFavouriteUseCaseImpl(
    private val rentalRepository: RentalRepository
) : DeleteFavouriteUseCase {
    override suspend fun invoke(id: Int): Result<Unit> {
        return rentalRepository.deleteFavourite(id)
    }
}
