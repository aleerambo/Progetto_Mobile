package com.corsolp.data.repository

import com.corsolp.data.remote.StudentHomeApi
import com.corsolp.domain.models.RentalType
import com.corsolp.domain.repository.RentalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RentalRepositoryImpl(
    private val studentHomeApi: StudentHomeApi
): RentalRepository {
    private val scope = CoroutineScope(Dispatchers.Main)

    private val _rentalTypeList = MutableStateFlow<List<RentalType>>(listOf())
    override val rentalTypeList: StateFlow<List<RentalType>> = _rentalTypeList

    private val rentalTypes = listOf(
        RentalType.Room(
            roomDescription = "Cozy Room",
            roomPictureUrl = "https://example.com/room.jpg",
            roomRooms = 1,
            roomSurface = 20,
            roomFloor = 1,
            roomServices = listOf("WiFi", "Air Conditioning"),
            roomPrice = 50.0
        ),
        RentalType.Apartment(
            apartmentDescription = "Spacious Apartment",
            apartmentPictureUrl = "https://example.com/apartment.jpg",
            apartmentRooms = 3,
            apartmentSurface = 80,
            apartmentFloor = 2,
            apartmentServices = listOf("WiFi", "Parking"),
            apartmentPrice = 120.0
        ),
        RentalType.Bed(
            bedSpaceDescription = "Shared Bed Space",
            bedSpacePictureUrl = "https://example.com/bedspace.jpg",
            bedSpaceRooms = 1,
            bedSpaceSurface = 15,
            bedSpaceFloor = 1,
            bedSpaceServices = listOf("WiFi"),
            bedSpacePrice = 30.0
        )
    )

    override fun fetchRentalTypeList() {
        scope.launch {
            val getAllRentalPosts = studentHomeApi.getAllRentalPosts()
            println("getAllRentalPosts: $getAllRentalPosts")
            _rentalTypeList.emit(rentalTypes)
        }
    }

}