// domain/usecases/FetchRentalPostByIdUseCase.kt
package com.corsolp.domain.usecases

import com.corsolp.domain.models.Rental
import com.corsolp.domain.repository.RentalRepository

/**
 * Interfaccia: invocabile come “(id: Int) → Result<Annuncio>”
 */
interface FetchRentalPostByIdUseCase : suspend (Int) -> Result<Rental>

/**
 * Implementazione: chiama rentalRepository.fetchRentalPostById()
 */
class FetchRentalPostByIdUseCaseImpl(
    private val rentalRepository: RentalRepository
) : FetchRentalPostByIdUseCase {
    override suspend fun invoke(id: Int): Result<Rental> {
        return rentalRepository.fetchRentalPostById(id)
    }
}
