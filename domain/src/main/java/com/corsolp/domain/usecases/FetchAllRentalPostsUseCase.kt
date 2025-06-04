// domain/usecases/FetchAllRentalPostsUseCase.kt
package com.corsolp.domain.usecases

import com.corsolp.domain.models.Rental
import com.corsolp.domain.repository.RentalRepository
import kotlinx.coroutines.flow.StateFlow

/**
 * Interfaccia: invocabile come “() → StateFlow<List<Annuncio>>”
 */
interface FetchAllRentalPostsUseCase : () -> StateFlow<List<Rental>>

/**
 * Implementazione: restituisce direttamente il Flow dal repository
 */
class FetchAllRentalPostsUseCaseImpl(
    private val rentalRepository: RentalRepository
) : FetchAllRentalPostsUseCase {
    override fun invoke(): StateFlow<List<Rental>> {
        return rentalRepository.fetchAllRentalPosts()
    }
}
