package com.corsolp.domain.usecases

import com.corsolp.domain.models.Neighborhood
import com.corsolp.domain.repository.RentalRepository
import kotlinx.coroutines.flow.StateFlow

/**
 * Interfaccia: invocabile come “() → StateFlow<List<Neighborhood>>”
 */
interface FetchNeighborhoodListUseCase : () -> StateFlow<List<Neighborhood>>

/**
 * Implementazione: restituisce direttamente il Flow dal repository
 */
class FetchNeighborhoodListUseCaseImpl(
    private val rentalRepository: RentalRepository
) : FetchNeighborhoodListUseCase {
    override fun invoke(): StateFlow<List<Neighborhood>> {
        return rentalRepository.fetchNeighborhoodList()
    }
}
