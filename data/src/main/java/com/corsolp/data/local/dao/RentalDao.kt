package com.corsolp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.corsolp.data.local.entities.RentalLocalModel

@Dao
interface RentalDao {
    @Query("SELECT * FROM rentals ORDER BY id DESC")
    fun getAllRentals(): List<RentalLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRentalLocalModel(models: List<RentalLocalModel>)

    // Rimuove tutti i record il cui id **non** è nella lista passata
    @Query("DELETE FROM rentals WHERE id NOT IN (:ids)")
    suspend fun deleteNotIn(ids: List<Int>)
}
