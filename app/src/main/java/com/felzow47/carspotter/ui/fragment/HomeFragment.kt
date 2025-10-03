package com.felzow47.carspotter.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.felzow47.carspotter.R
import com.felzow47.carspotter.ui.activity.CameraActivity
import com.felzow47.carspotter.ui.activity.SettingsActivity
import com.felzow47.carspotter.ui.fragment.CollectionFragment
import com.felzow47.carspotter.ui.viewmodel.VoitureViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: VoitureViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[VoitureViewModel::class.java]

        setupButtons(view)
    }

    private fun setupButtons(view: View) {
        // Bouton capture photo
        view.findViewById<Button>(R.id.btn_capture_photo)?.setOnClickListener {
            startActivity(Intent(requireContext(), CameraActivity::class.java))
        }

        // Bouton voir collection
        view.findViewById<Button>(R.id.btn_view_collection)?.setOnClickListener {
            // Navigation vers le fragment collection
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, CollectionFragment())
                .addToBackStack(null)
                .commit()
        }

        // Bouton paramètres
        view.findViewById<Button>(R.id.btn_settings)?.setOnClickListener {
            startActivity(Intent(requireContext(), SettingsActivity::class.java))
        }
    }
}
