package com.corsolp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.corsolp.data.local.entities.NewsLocalModel

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAllNews(): List<NewsLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(models: List<NewsLocalModel>)

    // Rimuove tutti i record il cui id **non** è nella lista passata
    @Query("DELETE FROM news WHERE id NOT IN (:ids)")
    suspend fun deleteNotIn(ids: List<Int>)
}
