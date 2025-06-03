package com.corsolp.data.di

import com.corsolp.data.remote.RetrofitClient
import com.corsolp.data.repository.RentalRepositoryImpl
import com.corsolp.domain.di.RepositoryProvider
import com.corsolp.domain.repository.RentalRepository

class RepositoryProviderImpl: RepositoryProvider {
    private val retrofitClient = RetrofitClient()

    override val rentalRepository: RentalRepository = RentalRepositoryImpl(
        rentApi = retrofitClient.rentApi
    )
}