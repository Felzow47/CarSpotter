package com.felzow47.carspotter.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.felzow47.carspotter.data.entity.VehiculeType
import com.felzow47.carspotter.databinding.ActivityAddVoitureBinding
import com.felzow47.carspotter.ui.viewmodel.VoitureViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddVoitureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddVoitureBinding
    private val viewModel: VoitureViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var currentLocation: Location? = null

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
        private const val CAMERA_PERMISSION_REQUEST_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddVoitureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupLocationClient()
        setupUI()
        setupClickListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ajouter une voiture"
    }

    private fun setupLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun setupUI() {
        // Configuration des spinners et autres composants UI
        val vehiculeTypes = VehiculeType.entries.map { it.displayName }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, vehiculeTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerType.adapter = adapter  // Suppression du safe call inutile
    }

    private fun setupClickListeners() {
        // TODO: Ajouter ces boutons au layout activity_add_voiture.xml
        /*
        binding.buttonTakePhoto?.setOnClickListener {
            checkCameraPermission()
        }

        binding.buttonGetLocation?.setOnClickListener {
            getCurrentLocation()
        }

        binding.buttonSave?.setOnClickListener {
            saveVoiture()
        }
        */
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            openCamera()
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    private fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            currentLocation = location
            location?.let {
                // TODO: Ajouter textViewLocation au layout ou utiliser un autre moyen d'affichage
                // binding.textViewLocation?.text = "Lat: ${it.latitude}, Lng: ${it.longitude}"

                // Alternative temporaire avec Toast
                Toast.makeText(this, "Location: ${it.latitude}, ${it.longitude}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveVoiture() {
        Toast.makeText(this, "Voiture ajoutée avec succès!", Toast.LENGTH_SHORT).show()
        finish()
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
