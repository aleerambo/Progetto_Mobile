package com.corsolp.domain.usecases

import com.corsolp.domain.models.News
import com.corsolp.domain.repository.RentalRepository
import kotlinx.coroutines.flow.StateFlow

/**
 * Interfaccia: invocabile come “() → StateFlow<List<News>>”
 */
interface FetchNewsUseCase : () -> StateFlow<List<News>>

/**
 * Implementazione: restituisce direttamente il Flow dal repository
 */
class FetchNewsUseCaseImpl(
    private val rentalRepository: RentalRepository
) : FetchNewsUseCase {
    override fun invoke(): StateFlow<List<News>> {
        return rentalRepository.fetchNewsList()
    }
}
