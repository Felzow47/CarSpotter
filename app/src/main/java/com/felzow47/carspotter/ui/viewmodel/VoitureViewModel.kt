package com.felzow47.carspotter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felzow47.carspotter.data.entity.Voiture
import com.felzow47.carspotter.repository.VoitureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoitureViewModel @Inject constructor(
    private val repository: VoitureRepository
) : ViewModel() {

    fun getAllVoitures(): LiveData<List<Voiture>> = repository.getAllVoitures()

    fun getVoitureById(id: Long): LiveData<Voiture?> = repository.getVoitureById(id)

    fun getFavoriteVoitures(): LiveData<List<Voiture>> = repository.getFavoriteVoitures()

    fun searchVoitures(query: String): LiveData<List<Voiture>> = repository.searchVoitures("%$query%")

    fun getVoitureCount(): LiveData<Int> = repository.getVoitureCount()

    fun insertVoiture(voiture: Voiture) {
        viewModelScope.launch {
            repository.insertVoiture(voiture)
        }
    }

    fun updateVoiture(voiture: Voiture) {
        viewModelScope.launch {
            repository.updateVoiture(voiture)
        }
    }

    fun deleteVoiture(voiture: Voiture) {
        viewModelScope.launch {
            repository.deleteVoiture(voiture)
        }
    }

    fun archiveVoiture(id: Long) {
        viewModelScope.launch {
            repository.archiveVoiture(id)
        }
    }

    fun toggleFavorite(id: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(id, isFavorite)
        }
    }
}
