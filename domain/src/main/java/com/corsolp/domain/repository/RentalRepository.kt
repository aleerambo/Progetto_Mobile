package com.corsolp.domain.repository

import com.corsolp.domain.models.Favourite
import kotlinx.coroutines.flow.StateFlow
import com.corsolp.domain.models.News
import com.corsolp.domain.models.Rental
import com.corsolp.domain.models.Service
import com.corsolp.domain.models.Neighborhood

interface RentalRepository {
    // 1) Liste “live” di dati rilasciati via StateFlow:
    fun fetchNewsList(): StateFlow<List<News>>
    fun fetchRentalTypeList(): StateFlow<List<RentalType>>
    fun fetchServiceList(): StateFlow<List<Service>>
    fun fetchNeighborhoodList(): StateFlow<List<Neighborhood>>
    fun fetchAllRentalPosts(): StateFlow<List<Rental>>
    fun fetchFavouritesList(): StateFlow<List<Favourite>>

    // 2) Operazioni “one‐shot” (suspend) che restituiscono Result<…>:
    suspend fun fetchNewsById(id: Int): Result<News>
    suspend fun fetchRentalPostById(id: Int): Result<Rental>
    suspend fun createRentalPost(
        idArea: Int,
        price: Double,
        description: String,
        rooms: Int,
        squareMeters: Int,
        floor: Int,
        address: String,
        selectedServices: List<Int>,
        type: Int,
        numberOfTenants: Int,
        minContract: Int,
        maxContract: Int
    ): Result<Unit>
    suspend fun updateRentalPost(
        id: Int,
        idArea: Int,
        price: Double,
        description: String,
        rooms: Int,
        squareMeters: Int,
        floor: Int,
        address: String,
        selectedServices: List<Int>,
        type: Int,
        numberOfTenants: Int,
        minContract: Int,
        maxContract: Int
    ): Result<Unit>
    suspend fun activateRentalPost(id: Int): Result<Unit>
    suspend fun deleteRentalPost(id: Int): Result<Unit>
    suspend fun addFavourite(rentalPostId: Int): Result<Unit>
    suspend fun deleteFavourite(id: Int): Result<Unit>
}
