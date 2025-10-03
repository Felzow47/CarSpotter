package com.felzow47.carspotter.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.felzow47.carspotter.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            // Utilisation du layout ultra-simple sans fragments ni navigation
            setContentView(R.layout.activity_main_minimal)

            // Configuration de la toolbar
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.title = "CarSpotter"

            // Configuration des boutons
            setupClickListeners()

            // Message de succès dans les logs
            println("🎉 MainActivity lancée avec succès avec layout minimal !")

        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback ultime
            Toast.makeText(this, "Erreur de layout : ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun setupClickListeners() {
        try {
            // Bouton ajouter une voiture
            findViewById<Button>(R.id.btn_add_car).setOnClickListener {
                Toast.makeText(this, "📸 Fonctionnalité en développement", Toast.LENGTH_SHORT).show()
            }

            // Bouton voir collection
            findViewById<Button>(R.id.btn_view_collection).setOnClickListener {
                Toast.makeText(this, "🚗 Collection en développement", Toast.LENGTH_SHORT).show()
            }

            // Bouton paramètres
            findViewById<Button>(R.id.btn_settings).setOnClickListener {
                Toast.makeText(this, "⚙️ Paramètres en développement", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Erreur boutons : ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
