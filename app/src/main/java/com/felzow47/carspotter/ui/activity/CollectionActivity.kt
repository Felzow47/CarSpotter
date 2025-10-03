package com.felzow47.carspotter.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felzow47.carspotter.R
import com.felzow47.carspotter.ui.adapter.VoitureAdapter
import com.felzow47.carspotter.ui.viewmodel.VoitureViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionActivity : AppCompatActivity() {

    private lateinit var viewModel: VoitureViewModel
    private lateinit var adapter: VoitureAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyText: TextView
    private lateinit var fabAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)

        setupViews()
        setupViewModel()
        observeData()
    }

    private fun setupViews() {
        // Toolbar
        findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)?.let { toolbar ->
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "🚗 Ma Collection"
        }

        // RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
        emptyText = findViewById(R.id.empty_text)
        fabAdd = findViewById(R.id.fab_add)

        // Configuration RecyclerView
        adapter = VoitureAdapter { voiture ->
            // TODO: Ouvrir les détails de la voiture
            val intent = Intent(this, VoitureDetailActivity::class.java)
            intent.putExtra("voiture_id", voiture.id)
            startActivity(intent)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CollectionActivity)
            adapter = this@CollectionActivity.adapter
        }

        // FAB pour ajouter une voiture
        fabAdd.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[VoitureViewModel::class.java]
    }

    private fun observeData() {
        viewModel.allVoitures.observe(this) { voitures ->
            if (voitures.isEmpty()) {
                recyclerView.visibility = android.view.View.GONE
                emptyText.visibility = android.view.View.VISIBLE
            } else {
                recyclerView.visibility = android.view.View.VISIBLE
                emptyText.visibility = android.view.View.GONE
                adapter.submitList(voitures)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
