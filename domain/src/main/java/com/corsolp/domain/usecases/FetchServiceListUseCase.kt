package com.corsolp.domain.usecases

import com.corsolp.domain.models.Service
import com.corsolp.domain.repository.RentalRepository
import kotlinx.coroutines.flow.StateFlow

/**
 * Interfaccia: invocabile come “() → StateFlow<List<Service>>”
 */
interface FetchServiceListUseCase : () -> StateFlow<List<Service>>

/**
 * Implementazione: restituisce direttamente il Flow dal repository
 */
class FetchServiceListUseCaseImpl(
    private val rentalRepository: RentalRepository
) : FetchServiceListUseCase {
    override fun invoke(): StateFlow<List<Service>> {
        return rentalRepository.fetchServiceList()
    }
}
