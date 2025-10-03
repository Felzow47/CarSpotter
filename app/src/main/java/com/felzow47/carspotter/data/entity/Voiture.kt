package com.felzow47.carspotter.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Entity(tableName = "voitures")
@Parcelize
data class Voiture(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val marque: String,
    val modele: String,
    val type: VehiculeType,
    val version: String? = null,
    val couleur: String? = null,
    val dateSortie: String? = null,
    val immatriculation: String? = null,
    val moteur: String? = null,
    val puissance: String? = null,
    val carburant: String? = null,
    val transmission: String? = null,
    val lieuObservation: String? = null,
    val dateObservation: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val tags: String = "", // Stocké comme string séparée par des virgules
    val notes: String? = null,
    val dateCreation: Long = System.currentTimeMillis(),
    val dateModification: Long = System.currentTimeMillis(),
    val isArchived: Boolean = false,
    val isFavorite: Boolean = false
) : Parcelable {

    fun getTagsList(): List<String> {
        return if (tags.isBlank()) emptyList()
        else tags.split(",").map { it.trim() }.filter { it.isNotBlank() }
    }

    fun setTagsList(tagsList: List<String>): Voiture {
        return this.copy(tags = tagsList.joinToString(", "))
    }
}
