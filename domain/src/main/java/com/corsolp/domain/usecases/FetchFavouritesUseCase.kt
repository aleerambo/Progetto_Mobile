package com.corsolp.domain.usecases

import com.corsolp.domain.models.Favourite
import com.corsolp.domain.repository.RentalRepository
import kotlinx.coroutines.flow.StateFlow

/**
 * Interfaccia: invocabile come “() → StateFlow<List<Favourite>>”
 */
interface FetchFavouritesUseCase : () -> StateFlow<List<Favourite>>

/**
 * Implementazione: restituisce direttamente il Flow dal repository
 */
class FetchFavouritesUseCaseImpl(
    private val rentalRepository: RentalRepository
) : FetchFavouritesUseCase {
    override fun invoke(): StateFlow<List<Favourite>> {
        return rentalRepository.fetchFavouritesList()
    }
}
