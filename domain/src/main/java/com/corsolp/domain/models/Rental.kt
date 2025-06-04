package com.corsolp.domain.models

enum class RentalTypeEnum {
    ROOM,
    APARTMENT,
    BED
}

data class Rental(
    val id: Int,
    val description: String,
    val pictureUrl: String?,
    val rooms: Int,
    val surface: Int,
    val floor: Int,
    val services: List<String>,
    val price: Double,
    val favorite: Boolean,
    val type: RentalTypeEnum
)