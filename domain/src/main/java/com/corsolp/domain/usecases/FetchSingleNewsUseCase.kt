package com.corsolp.domain.usecases

import com.corsolp.domain.models.News
import com.corsolp.domain.repository.RentalRepository

/**
 * Interfaccia: invocabile come “(id: Int) → Result<News>”
 */
interface FetchSingleNewsUseCase : suspend (Int) -> Result<News>

/**
 * Implementazione: chiama rentalRepository.fetchNewsById()
 */
class FetchSingleNewsUseCaseImpl(
    private val rentalRepository: RentalRepository
) : FetchSingleNewsUseCase {
    override suspend fun invoke(id: Int): Result<News> {
        return rentalRepository.fetchNewsById(id)
    }
}
