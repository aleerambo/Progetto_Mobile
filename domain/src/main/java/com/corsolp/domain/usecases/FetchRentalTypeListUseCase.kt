package com.corsolp.domain.usecases

import com.corsolp.domain.models.RentalType
import com.corsolp.domain.repository.RentalRepository
import kotlinx.coroutines.flow.StateFlow

interface FetchRentalTypeListUseCase: () -> StateFlow<List<RentalType>>

class FetchRentalTypeListUseCaseImpl(
    private val rentalRepository: RentalRepository
): FetchRentalTypeListUseCase{
    // Metodo che partendo dall UI dovrò richiamare per ottenere il repository
    override fun invoke(): StateFlow<List<RentalType>> {
        return rentalRepository.rentalTypeList
    }

}