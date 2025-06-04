package com.corsolp.domain.usecases

import com.corsolp.domain.models.RentalType
import com.corsolp.domain.repository.RentalRepository
import kotlinx.coroutines.flow.StateFlow

/**
 * Interfaccia: invocabile come “() → StateFlow<List<RentalType>>”
 */
interface FetchRentalTypeListUseCase : () -> StateFlow<List<RentalType>>

/**
 * Implementazione: restituisce direttamente il Flow dal repository
 */
class FetchRentalTypeListUseCaseImpl(
    private val rentalRepository: RentalRepository
) : FetchRentalTypeListUseCase {
    override fun invoke(): StateFlow<List<RentalType>> {
        return rentalRepository.fetchRentalTypeList()
    }
}