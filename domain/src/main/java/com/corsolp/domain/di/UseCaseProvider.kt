package com.corsolp.domain.di

import com.corsolp.domain.usecases.FetchRentalTypeListUseCase
import com.corsolp.domain.usecases.FetchRentalTypeListUseCaseImpl

// Singleton -> rimane in memoria per tutta la vita dell'applicazione
object UseCaseProvider {
    lateinit var fetchRentalTypeListUseCase: FetchRentalTypeListUseCase

    fun setup(
        repositoryProvider: RepositoryProvider
    ) {
        fetchRentalTypeListUseCase = FetchRentalTypeListUseCaseImpl(
            rentalRepository = repositoryProvider.rentalRepository
        )
    }
}