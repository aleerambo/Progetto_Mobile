package com.corsolp.domain.repository

import com.corsolp.domain.models.RentalType
import kotlinx.coroutines.flow.StateFlow

interface RentalRepository {
    val rentalTypeList: StateFlow<List<RentalType>>

    fun fetchRentalTypeList()
}