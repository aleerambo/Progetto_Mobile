package com.corsolp.data.repository

import com.corsolp.data.remote.RentApi
import com.corsolp.domain.models.RentalType
import com.corsolp.domain.repository.RentalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RentalRepositoryImpl(
    private val rentApi: RentApi
) : RentalRepository {
    private val scope = CoroutineScope(Dispatchers.Main)

    private val _rentalTypeList = MutableStateFlow<List<RentalType>>(listOf())
    override val rentalTypeList: StateFlow<List<RentalType>> = _rentalTypeList

    override fun fetchRentalTypeList() {
        scope.launch {
            val getLastRentalPosts = rentApi.getLastRentalPosts()
            println("getLastRentalPosts: $getLastRentalPosts")

            val rentalTypes = getLastRentalPosts.map { data ->
                when (data.tipologia) {
                    "stanza" -> RentalType.Room(
                        roomDescription = data.descrizione ?: "",
                        roomPictureUrl = data.fotoAnnuncio ?: "",
                        roomRooms = data.locali ?: 0,
                        roomSurface = data.mq ?: 0,
                        roomFloor = data.piano ?: 0,
                        roomServices = data.servizi?.split(',') ?: listOf(),
                        roomPrice = data.prezzo?.toDouble() ?: 0.0
                    )
                    "appartamento" -> RentalType.Apartment(
                        apartmentDescription = data.descrizione ?: "",
                        apartmentPictureUrl = data.fotoAnnuncio ?: "",
                        apartmentRooms = data.locali ?: 0,
                        apartmentSurface = data.mq ?: 0,
                        apartmentFloor = data.piano ?: 0,
                        apartmentServices = data.servizi?.split(',') ?: listOf(),
                        apartmentPrice = data.prezzo?.toDouble() ?: 0.0
                    )
                    "posto letto" -> RentalType.Bed(
                        bedSpaceDescription = data.descrizione ?: "",
                        bedSpacePictureUrl = data.fotoAnnuncio ?: "",
                        bedSpaceRooms = data.locali ?: 0,
                        bedSpaceSurface = data.mq ?: 0,
                        bedSpaceFloor = data.piano ?: 0,
                        bedSpaceServices = data.servizi?.split(',') ?: listOf(),
                        bedSpacePrice = data.prezzo?.toDouble() ?: 0.0
                    )
                    else -> throw IllegalArgumentException("Unknown rental type: ${data.tipologia}")
                }
            }

            _rentalTypeList.emit(rentalTypes)
        }
    }
}