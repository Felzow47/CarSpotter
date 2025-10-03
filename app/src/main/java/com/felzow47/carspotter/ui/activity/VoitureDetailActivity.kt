package com.felzow47.carspotter.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.felzow47.carspotter.databinding.ActivityVoitureDetailBinding
import com.felzow47.carspotter.ui.adapter.PhotoAdapter
import com.felzow47.carspotter.ui.viewmodel.VoitureViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoitureDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVoitureDetailBinding
    private val viewModel: VoitureViewModel by viewModels()
    private lateinit var photoAdapter: PhotoAdapter
    private var voitureId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVoitureDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        voitureId = intent.getLongExtra("voiture_id", -1)

        if (voitureId == -1L) {
            finish()
            return
        }

        setupToolbar()
        setupRecyclerView()
        loadVoitureDetails()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Détails de la voiture"
    }

    private fun setupRecyclerView() {
        photoAdapter = PhotoAdapter { _ ->  // Renommage du paramètre inutilisé
            // Gérer le clic sur une photo
        }

        binding.recyclerViewPhotos.apply {
            layoutManager = LinearLayoutManager(this@VoitureDetailActivity)
            adapter = photoAdapter
        }
    }

    private fun loadVoitureDetails() {
        viewModel.getVoitureById(voitureId).observe(this) { voiture ->
            voiture?.let {
                displayVoitureDetails(it)
            }
        }
    }

    private fun displayVoitureDetails(@Suppress("UNUSED_PARAMETER") voiture: Any) {
        // Implémentation à compléter selon votre modèle de données
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()  // Remplacement de la méthode dépréciée
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
