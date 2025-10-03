package com.felzow47.carspotter.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.felzow47.carspotter.R
import com.felzow47.carspotter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Configuration simple de la toolbar si elle existe
            setSupportActionBar(findViewById(R.id.toolbar))

            // Configuration basique sans navigation complexe pour éviter les crashes
            supportActionBar?.title = "CarSpotter"

        } catch (e: Exception) {
            // En cas d'erreur, on utilise un layout simple
            setContentView(R.layout.activity_main)
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // TODO: Ouvrir les paramètres
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
