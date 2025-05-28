package com.corsolp.domain.di

import com.corsolp.domain.repository.RentalRepository

interface RepositoryProvider {
    val rentalRepository: RentalRepository
}