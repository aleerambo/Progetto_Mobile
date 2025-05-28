package com.corsolp.domain.models

sealed class RentalType (
    val description: String,
    val pictureUrl: String,
    val rooms: Int,
    val surface: Int,
    val floor: Int,
    val services: List<String>,
    val price: Double,
) {
    data class Room(
        private val roomDescription: String,
        private val roomPictureUrl: String,
        private val roomRooms: Int,
        private val roomSurface: Int,
        private val roomFloor: Int,
        private val roomServices: List<String>,
        private val roomPrice: Double
    ) : RentalType(
        description = roomDescription,
        pictureUrl = roomPictureUrl,
        rooms = roomRooms,
        surface = roomSurface,
        floor = roomFloor,
        services = roomServices,
        price = roomPrice
    )

    data class Apartment(
        private val apartmentDescription: String,
        private val apartmentPictureUrl: String,
        private val apartmentRooms: Int,
        private val apartmentSurface: Int,
        private val apartmentFloor: Int,
        private val apartmentServices: List<String>,
        private val apartmentPrice: Double
    ) : RentalType(
        description = apartmentDescription,
        pictureUrl = apartmentPictureUrl,
        rooms = apartmentRooms,
        surface = apartmentSurface,
        floor = apartmentFloor,
        services = apartmentServices,
        price = apartmentPrice
    )

    data class Bed(
        private val bedSpaceDescription: String,
        private val bedSpacePictureUrl: String,
        private val bedSpaceRooms: Int,
        private val bedSpaceSurface: Int,
        private val bedSpaceFloor: Int,
        private val bedSpaceServices: List<String>,
        private val bedSpacePrice: Double
    ) : RentalType(
        description = bedSpaceDescription,
        pictureUrl = bedSpacePictureUrl,
        rooms = bedSpaceRooms,
        surface = bedSpaceSurface,
        floor = bedSpaceFloor,
        services = bedSpaceServices,
        price = bedSpacePrice
    )
}