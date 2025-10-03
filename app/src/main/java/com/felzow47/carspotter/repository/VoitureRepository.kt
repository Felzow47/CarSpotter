package com.felzow47.carspotter.repository

import androidx.lifecycle.LiveData
import com.felzow47.carspotter.data.dao.VoitureDao
import com.felzow47.carspotter.data.entity.VehiculeType
import com.felzow47.carspotter.data.entity.Voiture
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VoitureRepository @Inject constructor(private val voitureDao: VoitureDao) {

    fun getAllVoitures(): LiveData<List<Voiture>> = voitureDao.getAllVoitures()

    fun getVoitureById(id: Long): LiveData<Voiture?> = voitureDao.getVoitureById(id)

    fun getFavoriteVoitures(): LiveData<List<Voiture>> = voitureDao.getFavoriteVoitures()

    fun searchVoitures(query: String): LiveData<List<Voiture>> = voitureDao.searchVoitures(query)

    fun getVoituresByType(type: VehiculeType): LiveData<List<Voiture>> = voitureDao.getVoituresByType(type)

    fun getVoitureCount(): LiveData<Int> = voitureDao.getVoitureCount()

    suspend fun insertVoiture(voiture: Voiture): Long = voitureDao.insertVoiture(voiture)

    suspend fun updateVoiture(voiture: Voiture) = voitureDao.updateVoiture(voiture)

    suspend fun deleteVoiture(voiture: Voiture) = voitureDao.deleteVoiture(voiture)

    suspend fun archiveVoiture(id: Long) = voitureDao.archiveVoiture(id)

    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean) = voitureDao.updateFavoriteStatus(id, isFavorite)
}
