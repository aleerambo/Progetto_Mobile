package com.corsolp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.corsolp.data.local.dao.NewsDao
import com.corsolp.data.local.dao.RentalDao
import com.corsolp.data.local.entities.NewsLocalModel
import com.corsolp.data.local.entities.RentalLocalModel

@Database(
    entities = [
        RentalLocalModel::class,
        NewsLocalModel::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rentalDao(): RentalDao
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
    }
}
