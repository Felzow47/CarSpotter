package com.felzow47.carspotter.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.felzow47.carspotter.R
import com.felzow47.carspotter.databinding.ActivityMainBinding
import com.felzow47.carspotter.ui.activity.CameraActivity
import com.felzow47.carspotter.ui.activity.SettingsActivity
import com.felzow47.carspotter.ui.adapter.VoitureCardAdapter
import com.felzow47.carspotter.ui.viewmodel.VoitureViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var voitureViewModel: VoitureViewModel
    private lateinit var voitureAdapter: VoitureCardAdapter

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Choix de l'interface selon les préférences utilisateur
        val useCardsInterface = sharedPreferences.getBoolean("use_cards_interface", true)

        try {
            if (useCardsInterface) {
                // 🎨 Interface CARDS magnifique par défaut !
                setupCardsInterface()
            } else {
                // 🧭 Interface navigation fragments (choix utilisateur)
                setupNavigationInterface()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // 🛡️ FALLBACK vers navigation si interface cards échoue
            setupNavigationInterface()
        }
    }

    private fun setupCardsInterface() {
        // Utilisation du layout avec vos magnifiques cards !
        setContentView(R.layout.activity_main_cards)

        // Configuration de la toolbar avec menu hamburger
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configuration du DrawerLayout et NavigationView
        val drawerLayout = findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<com.google.android.material.navigation.NavigationView>(R.id.nav_view)

        // Configuration du menu hamburger dans la toolbar
        appBarConfiguration = AppBarConfiguration(setOf(), drawerLayout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Utiliser l'icône système standard pour le menu hamburger
        supportActionBar?.setHomeAsUpIndicator(androidx.appcompat.R.drawable.abc_ic_ab_back_material)

        // Gestion des clics sur les éléments du menu drawer
        navView?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Déjà sur la page d'accueil
                    android.widget.Toast.makeText(this, "🏠 Déjà sur l'accueil", android.widget.Toast.LENGTH_SHORT).show()
                }
                R.id.nav_collection -> {
                    // Déjà sur la collection avec les cards
                    android.widget.Toast.makeText(this, "🚗 Vous êtes dans la collection", android.widget.Toast.LENGTH_SHORT).show()
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                }
                // Ajouter d'autres éléments du menu si nécessaire
            }
            drawerLayout.closeDrawers()
            true
        }

        // Configuration du FAB avec choix utilisateur
        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)?.setOnClickListener {
            handleFabClick()
        }

        // Configuration du RecyclerView avec vos superbes cards
        setupRecyclerView()

        // Configuration du ViewModel
        setupViewModel()

        println("🎨 Interface CARDS avec menu hamburger lancée - Magnifique !")
    }

    private fun setupNavigationInterface() {
        try {
            // Interface navigation fragments (fallback ou choix utilisateur)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Accès direct aux éléments via findViewById car binding.appBarMain n'existe pas
            val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)

            val fab = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)
            fab?.setOnClickListener {
                handleFabClick()
            }

            // Vérifier que le NavHostFragment existe avant de configurer la navigation
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
            if (navHostFragment != null) {
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                appBarConfiguration = AppBarConfiguration(navController.graph)
                setupActionBarWithNavController(navController, appBarConfiguration)

                println("🧭 Interface NAVIGATION lancée avec succès")
            } else {
                // Si le NavHostFragment n'existe pas, fallback vers l'interface Cards
                println("⚠️ NavHostFragment introuvable, fallback vers Cards")
                throw Exception("Navigation fragments non disponibles")
            }

        } catch (e: Exception) {
            e.printStackTrace()
            println("❌ Erreur interface Navigation: ${e.message}")

            // Fallback de sécurité vers l'interface Cards
            android.widget.Toast.makeText(this,
                "⚠️ Interface Navigation indisponible\n🎨 Basculement vers Cards",
                android.widget.Toast.LENGTH_LONG).show()

            // Forcer le retour à l'interface Cards
            sharedPreferences.edit().putBoolean("use_cards_interface", true).apply()
            setupCardsInterface()
        }
    }

    private fun handleFabClick() {
        // Afficher un menu flottant avec les deux options
        showFabMenu()
    }

    private fun showFabMenu() {
        val fab = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)

        // Créer un PopupMenu ancré sur le FAB
        val popupMenu = androidx.appcompat.widget.PopupMenu(this, fab)

        // Ajouter les options du menu
        popupMenu.menu.add(0, 1, 0, "📸 Appareil photo")
        popupMenu.menu.add(0, 2, 1, "✏️ Entrée manuelle")

        // Gérer les clics sur les éléments du menu
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                1 -> {
                    // Option Appareil photo
                    startActivity(Intent(this, CameraActivity::class.java))
                    true
                }
                2 -> {
                    // Option Entrée manuelle
                    startActivity(Intent(this, AddVoitureActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Afficher le menu
        popupMenu.show()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_voitures)
        voitureAdapter = VoitureCardAdapter { voiture ->
            // TODO: Ouvrir détails de la voiture
            android.widget.Toast.makeText(this, "🚗 ${voiture.marque} ${voiture.modele}", android.widget.Toast.LENGTH_SHORT).show()
        }

        recyclerView.apply {
            adapter = voitureAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupViewModel() {
        voitureViewModel = ViewModelProvider(this)[VoitureViewModel::class.java]

        // Observer les voitures pour mettre à jour les cards
        voitureViewModel.allVoitures.observe(this) { voitures ->
            voitureAdapter.updateVoitures(voitures)

            // Mise à jour du compteur
            findViewById<TextView>(R.id.text_voiture_count)?.text =
                "${voitures.size} voiture${if (voitures.size > 1) "s" else ""} spotée${if (voitures.size > 1) "s" else ""}"

            // Affichage du message vide si nécessaire
            val emptyState = findViewById<android.widget.LinearLayout>(R.id.layout_empty_state)
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_voitures)

            if (voitures.isEmpty()) {
                emptyState?.visibility = android.view.View.VISIBLE
                recyclerView?.visibility = android.view.View.GONE
            } else {
                emptyState?.visibility = android.view.View.GONE
                recyclerView?.visibility = android.view.View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Gestion du bouton hamburger pour ouvrir/fermer le drawer
                val drawerLayout = findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawer_layout)
                if (drawerLayout != null) {
                    if (drawerLayout.isDrawerOpen(androidx.core.view.GravityCompat.START)) {
                        drawerLayout.closeDrawer(androidx.core.view.GravityCompat.START)
                    } else {
                        drawerLayout.openDrawer(androidx.core.view.GravityCompat.START)
                    }
                    true
                } else {
                    super.onOptionsItemSelected(item)
                }
            }
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // Gestion différente selon l'interface utilisée
        val drawerLayout = findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawer_layout)
        return if (drawerLayout != null) {
            // Interface cards avec drawer
            if (drawerLayout.isDrawerOpen(androidx.core.view.GravityCompat.START)) {
                drawerLayout.closeDrawer(androidx.core.view.GravityCompat.START)
                true
            } else {
                super.onSupportNavigateUp()
            }
        } else {
            // Interface navigation fragments
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }
    }
}
