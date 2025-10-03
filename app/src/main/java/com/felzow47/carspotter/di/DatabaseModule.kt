package com.felzow47.carspotter.di

import android.content.Context
import androidx.room.Room
import com.felzow47.carspotter.data.dao.VoitureDao
import com.felzow47.carspotter.data.database.CarSpotterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): CarSpotterDatabase {
        return Room.databaseBuilder(
            appContext,
            CarSpotterDatabase::class.java,
            "carspotter_database"
        ).build()
    }

    @Provides
    fun provideVoitureDao(database: CarSpotterDatabase): VoitureDao {
        return database.voitureDao()
    }
}
