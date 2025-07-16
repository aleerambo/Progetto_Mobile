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
    val services: String,    // es. "WiFi,Parking,Pool"
    val price: Double,
    val favorite: Boolean,
    val type: String            // es. "stanza", "appartamento", "posto letto"
)
