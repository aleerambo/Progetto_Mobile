package com.corsolp.domain.usecases

import com.corsolp.domain.repository.RentalRepository

//interface StartFetchRentalTypeListUseCase: () -> Unit OLD CODE

interface StartFetchRentalTypeListUseCase {
    fun invoke(location: String)
}

class StartFetchRentalTypeListUseCaseImpl(
    private val rentalRepository: RentalRepository
): StartFetchRentalTypeListUseCase {
    override fun invoke(location: String) {
        rentalRepository.fetchRentalTypeList()
    }
}
