package com.corsolp.domain.models

import kotlinx.serialization.Serializable

@Serializable
enum class RentalTypeEnum {
    ROOM,
    APARTMENT,
    BED
}

@Serializable
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
    val type: RentalTypeEnum,
    val address: String,
    val phoneNumber: String,
    val email: String,
)