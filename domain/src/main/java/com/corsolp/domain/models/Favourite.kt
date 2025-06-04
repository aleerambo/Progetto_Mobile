package com.corsolp.domain.models

data class Favourite(
    val id: Int,
    val userId: Int,
    val rental: Rental
)