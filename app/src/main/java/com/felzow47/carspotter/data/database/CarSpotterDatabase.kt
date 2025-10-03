package com.felzow47.carspotter.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.felzow47.carspotter.data.dao.VoitureDao
import com.felzow47.carspotter.data.entity.Voiture

@Database(
    entities = [Voiture::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CarSpotterDatabase : RoomDatabase() {

    abstract fun voitureDao(): VoitureDao

    companion object {
        @Volatile
        private var INSTANCE: CarSpotterDatabase? = null

        fun getDatabase(context: Context): CarSpotterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarSpotterDatabase::class.java,
                    "carspotter_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
