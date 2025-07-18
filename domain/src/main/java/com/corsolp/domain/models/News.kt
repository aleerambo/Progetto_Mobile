package com.corsolp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class News (
    val id: Int,
    val title: String,
    val content: String,
    val publishDate: String,
    val pictureUrl: String
)