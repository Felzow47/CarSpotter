package com.felzow47.carspotter.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.felzow47.carspotter.R
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setupViews()
        updateStatusTexts()
    }

    private fun setupViews() {
        // Toolbar
        findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)?.let { toolbar ->
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "⚙️ Paramètres"
        }

        // Configuration des cards
        setupCards()
    }

    private fun setupCards() {
        // Choix d'interface (Cards vs Navigation)
        findViewById<MaterialCardView>(R.id.card_interface_choice).setOnClickListener {
            showInterfaceChoiceDialog()
        }

        // Permissions (existante)
        findViewById<MaterialCardView>(R.id.card_permissions).setOnClickListener {
            Toast.makeText(this, "🔐 Gestion des permissions (à implémenter)", Toast.LENGTH_SHORT).show()
        }

        // Sauvegarde
        findViewById<MaterialCardView>(R.id.card_backup).setOnClickListener {
            Toast.makeText(this, "☁️ Sauvegarde cloud (à implémenter)", Toast.LENGTH_SHORT).show()
        }

        // Export
        findViewById<MaterialCardView>(R.id.card_export).setOnClickListener {
            Toast.makeText(this, "📤 Export données (à implémenter)", Toast.LENGTH_SHORT).show()
        }

        // À propos
        findViewById<MaterialCardView>(R.id.card_about).setOnClickListener {
            showAbout()
        }

        // GitHub
        findViewById<MaterialCardView>(R.id.card_github).setOnClickListener {
            openGitHub()
        }

        // Contact
        findViewById<MaterialCardView>(R.id.card_contact).setOnClickListener {
            Toast.makeText(this, "📧 Contact: Felzow47 sur GitHub", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateStatusTexts() {
        // Interface : Cards vs Navigation
        val useCardsInterface = sharedPreferences.getBoolean("use_cards_interface", true)
        val interfaceStatus = if (useCardsInterface) "🎨 Cards" else "🧭 Navigation"
        findViewById<TextView>(R.id.text_interface_status)?.text = interfaceStatus
    }

    private fun showInterfaceChoiceDialog() {
        val currentChoice = sharedPreferences.getBoolean("use_cards_interface", true)

        // Créer les textes avec des "boutons radio" visuels
        val cardsText = if (currentChoice) "🔵 🎨 Cards magnifiques (recommandé)" else "⚪ 🎨 Cards magnifiques (recommandé)"
        val navigationText = if (!currentChoice) "🔵 🧭 Navigation fragments" else "⚪ 🧭 Navigation fragments"

        AlertDialog.Builder(this)
            .setTitle("🚗 Choix de l'interface")
            .setMessage("Actuelle : ${if (currentChoice) "Cards" else "Navigation"}\n\nSélectionnez votre préférence :")
            .setPositiveButton(cardsText) { dialog, _ ->
                // Choisir Cards
                sharedPreferences.edit().putBoolean("use_cards_interface", true).apply()
                updateStatusTexts()
                Toast.makeText(this,
                    "✅ Interface Cards sélectionnée\n🔄 Redémarrez l'app pour appliquer",
                    Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }
            .setNegativeButton(navigationText) { dialog, _ ->
                // Choisir Navigation
                sharedPreferences.edit().putBoolean("use_cards_interface", false).apply()
                updateStatusTexts()
                Toast.makeText(this,
                    "✅ Interface Navigation sélectionnée\n🔄 Redémarrez l'app pour appliquer",
                    Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }
            .setNeutralButton("❌ Annuler") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showAbout() {
        Toast.makeText(this,
            "🚗 CarSpotter v0.1.0\n" +
                    "✅ Interface fonctionnelle\n" +
                    "📸 Caméra implémentée\n" +
                    "🚧 Fonctionnalités en développement",
            Toast.LENGTH_LONG).show()
    }

    private fun openGitHub() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Felzow47/CarSpotter"))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Impossible d'ouvrir GitHub", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
