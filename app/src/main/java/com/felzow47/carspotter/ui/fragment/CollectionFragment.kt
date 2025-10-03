package com.felzow47.carspotter.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felzow47.carspotter.R
import com.felzow47.carspotter.ui.activity.CameraActivity
import com.felzow47.carspotter.ui.adapter.VoitureCardAdapter
import com.felzow47.carspotter.ui.viewmodel.VoitureViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment : Fragment() {

    private lateinit var viewModel: VoitureViewModel
    private lateinit var adapter: VoitureCardAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
        setupViewModel()
        observeData()
    }

    private fun setupViews(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view)
        emptyText = view.findViewById(R.id.empty_text)

        // Configuration RecyclerView avec VoitureCardAdapter (celui qui fonctionne)
        adapter = VoitureCardAdapter { voiture ->
            // Afficher les détails dans un Toast pour éviter les crashs
            Toast.makeText(requireContext(), "🚗 ${voiture.marque} ${voiture.modele}", Toast.LENGTH_SHORT).show()
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[VoitureViewModel::class.java]
    }

    private fun observeData() {
        viewModel.allVoitures.observe(viewLifecycleOwner) { voitures ->
            if (voitures.isEmpty()) {
                recyclerView.visibility = View.GONE
                emptyText.visibility = View.VISIBLE
                emptyText.text = "🚗 Aucune voiture dans votre collection\n\nUtilisez le bouton + pour ajouter des voitures !"
            } else {
                recyclerView.visibility = View.VISIBLE
                emptyText.visibility = View.GONE
                adapter.updateVoitures(voitures)
            }
        }
    }
}
