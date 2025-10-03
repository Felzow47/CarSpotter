package com.felzow47.carspotter.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.felzow47.carspotter.R
import com.felzow47.carspotter.data.entity.Voiture
import com.felzow47.carspotter.data.entity.VehiculeType
import com.felzow47.carspotter.ui.viewmodel.VoitureViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddVoitureActivity : AppCompatActivity() {

    private lateinit var voitureViewModel: VoitureViewModel

    // Views principales
    private lateinit var editMarque: TextInputEditText
    private lateinit var editModele: TextInputEditText
    private lateinit var spinnerType: Spinner
    private lateinit var editVersion: TextInputEditText
    private lateinit var editCouleur: TextInputEditText
    private lateinit var editImmatriculation: TextInputEditText

    // Détails techniques
    private lateinit var editDateSortie: TextInputEditText
    private lateinit var editMoteur: TextInputEditText
    private lateinit var editPuissance: TextInputEditText
    private lateinit var editCarburant: TextInputEditText
    private lateinit var editTransmission: TextInputEditText

    // Localisation et notes
    private lateinit var editLieu: TextInputEditText
    private lateinit var editTags: TextInputEditText
    private lateinit var editNotes: TextInputEditText

    // Boutons
    private lateinit var buttonTakePhoto: Button
    private lateinit var buttonCancel: Button
    private lateinit var buttonSave: Button

    // Autres
    private lateinit var textPhotoCount: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_voiture)

        setupViews()
        setupViewModel()
        setupSpinner()
        setupListeners()
    }

    private fun setupViews() {
        // Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "✏️ Ajouter une voiture"

        // Informations principales
        editMarque = findViewById(R.id.edit_marque)
        editModele = findViewById(R.id.edit_modele)
        spinnerType = findViewById(R.id.spinner_type)
        editVersion = findViewById(R.id.edit_version)
        editCouleur = findViewById(R.id.edit_couleur)
        editImmatriculation = findViewById(R.id.edit_immatriculation)

        // Détails techniques
        editDateSortie = findViewById(R.id.edit_date_sortie)
        editMoteur = findViewById(R.id.edit_moteur)
        editPuissance = findViewById(R.id.edit_puissance)
        editCarburant = findViewById(R.id.edit_carburant)
        editTransmission = findViewById(R.id.edit_transmission)

        // Localisation et notes
        editLieu = findViewById(R.id.edit_lieu)
        editTags = findViewById(R.id.edit_tags)
        editNotes = findViewById(R.id.edit_notes)

        // Boutons et autres
        buttonTakePhoto = findViewById(R.id.button_take_photo)
        buttonCancel = findViewById(R.id.button_cancel)
        buttonSave = findViewById(R.id.button_save)
        textPhotoCount = findViewById(R.id.text_photo_count)
        progressBar = findViewById(R.id.progress_bar)
    }

    private fun setupViewModel() {
        voitureViewModel = ViewModelProvider(this)[VoitureViewModel::class.java]
    }

    private fun setupSpinner() {
        // Types de voitures correspondant à l'enum VehiculeType
        val types = VehiculeType.values()

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types.map { it.displayName })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.adapter = adapter
    }

    private fun setupListeners() {
        // Bouton prendre photo - lance CameraActivity
        buttonTakePhoto.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
            Toast.makeText(this, "📸 Redirection vers l'appareil photo", Toast.LENGTH_SHORT).show()
        }

        // Bouton annuler
        buttonCancel.setOnClickListener {
            finish()
        }

        // Bouton sauvegarder
        buttonSave.setOnClickListener {
            saveVoiture()
        }
    }

    private fun saveVoiture() {
        // Validation des champs obligatoires
        val marque = editMarque.text.toString().trim()
        val modele = editModele.text.toString().trim()

        if (marque.isEmpty()) {
            editMarque.error = "La marque est obligatoire"
            editMarque.requestFocus()
            return
        }

        if (modele.isEmpty()) {
            editModele.error = "Le modèle est obligatoire"
            editModele.requestFocus()
            return
        }

        // Afficher le progress bar
        progressBar.visibility = android.view.View.VISIBLE
        buttonSave.isEnabled = false

        // Récupérer le VehiculeType sélectionné
        val selectedTypeIndex = spinnerType.selectedItemPosition
        val selectedType = VehiculeType.values()[selectedTypeIndex]

        // Formater la date actuelle
        val currentDate = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault()).format(java.util.Date())

        // Créer l'objet Voiture avec les bons types
        val voiture = Voiture(
            marque = marque,
            modele = modele,
            type = selectedType, // VehiculeType enum
            version = editVersion.text.toString().trim().ifEmpty { null },
            couleur = editCouleur.text.toString().trim().ifEmpty { null },
            immatriculation = editImmatriculation.text.toString().trim().ifEmpty { null },
            dateSortie = editDateSortie.text.toString().trim().ifEmpty { null },
            moteur = editMoteur.text.toString().trim().ifEmpty { null },
            puissance = editPuissance.text.toString().trim().ifEmpty { null },
            carburant = editCarburant.text.toString().trim().ifEmpty { null },
            transmission = editTransmission.text.toString().trim().ifEmpty { null },
            lieuObservation = editLieu.text.toString().trim().ifEmpty { null },
            tags = editTags.text.toString().trim(), // String non-nullable (pas null)
            notes = editNotes.text.toString().trim().ifEmpty { null },
            dateObservation = currentDate, // String formatée (pas Date)
            isFavorite = false,
            isArchived = false
        )

        // Sauvegarder dans la base de données
        voitureViewModel.insertVoiture(voiture)

        // Message de succès
        Toast.makeText(this, "✅ Voiture $marque $modele ajoutée avec succès !", Toast.LENGTH_LONG).show()

        // Retourner à l'activité principale
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
