package com.corsolp.domain.usecases

import com.corsolp.domain.repository.RentalRepository

/**
 * Interfaccia: invocabile come “(rentalPostId: Int) → Result<Unit>”
 */
interface AddFavouriteUseCase : suspend (Int) -> Result<Unit>

/**
 * Implementazione: chiama rentalRepository.addFavourite()
 */
class AddFavouriteUseCaseImpl(
    private val rentalRepository: RentalRepository
) : AddFavouriteUseCase {
    override suspend fun invoke(rentalPostId: Int): Result<Unit> {
        return rentalRepository.addFavourite(rentalPostId)
    }
}
