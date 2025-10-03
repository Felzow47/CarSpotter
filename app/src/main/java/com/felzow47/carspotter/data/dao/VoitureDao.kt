package com.felzow47.carspotter.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.felzow47.carspotter.data.entity.VehiculeType
import com.felzow47.carspotter.data.entity.Voiture

@Dao
interface VoitureDao {

    @Query("SELECT * FROM voitures WHERE isArchived = 0 ORDER BY dateCreation DESC")
    fun getAllVoitures(): LiveData<List<Voiture>>

    @Query("SELECT * FROM voitures WHERE id = :id")
    fun getVoitureById(id: Long): LiveData<Voiture?>

    @Query("SELECT * FROM voitures WHERE isFavorite = 1 AND isArchived = 0 ORDER BY dateCreation DESC")
    fun getFavoriteVoitures(): LiveData<List<Voiture>>

    @Query("SELECT * FROM voitures WHERE (marque LIKE :searchQuery OR modele LIKE :searchQuery OR tags LIKE :searchQuery) AND isArchived = 0")
    fun searchVoitures(searchQuery: String): LiveData<List<Voiture>>

    @Query("SELECT * FROM voitures WHERE type = :type AND isArchived = 0 ORDER BY dateCreation DESC")
    fun getVoituresByType(type: VehiculeType): LiveData<List<Voiture>>

    @Insert
    suspend fun insertVoiture(voiture: Voiture): Long

    @Update
    suspend fun updateVoiture(voiture: Voiture)

    @Delete
    suspend fun deleteVoiture(voiture: Voiture)

    @Query("UPDATE voitures SET isArchived = 1 WHERE id = :id")
    suspend fun archiveVoiture(id: Long)

    @Query("UPDATE voitures SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean)

    @Query("SELECT COUNT(*) FROM voitures WHERE isArchived = 0")
    fun getVoitureCount(): LiveData<Int>
}
