package com.corsolp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rentals")
data class RentalLocalModel(
    @PrimaryKey val id: Int,
    val description: String,
    val pictureUrl: String?,
    val rooms: Int,
    val surface: Int,
    val floor: Int,
    val price: Double,
    val type: String,
    val phoneNumber: String,
    val email: String,
    val address: String,
    val favorite: Boolean
)
