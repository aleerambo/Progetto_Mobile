package com.corsolp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsLocalModel(
    @PrimaryKey val id: Int,
    val title: String,
    val content: String,
    val publishDate: String,
    val pictureUrl: String
)
