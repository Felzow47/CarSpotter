package com.felzow47.carspotter.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felzow47.carspotter.R
import com.felzow47.carspotter.data.entity.Voiture

class VoitureCardAdapter(
    private var voitures: List<Voiture> = emptyList(),
    private val onVoitureClick: (Voiture) -> Unit = {}
) : RecyclerView.Adapter<VoitureCardAdapter.VoitureViewHolder>() {

    class VoitureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageVoiture: ImageView = itemView.findViewById(R.id.image_voiture)
        val textMarque: TextView = itemView.findViewById(R.id.text_marque)
        val textModele: TextView = itemView.findViewById(R.id.text_modele)
        val iconFavorite: ImageView = itemView.findViewById(R.id.icon_favorite)
        val textPhotoCount: TextView = itemView.findViewById(R.id.text_photo_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoitureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_voiture_card, parent, false)
        return VoitureViewHolder(view)
    }

    override fun onBindViewHolder(holder: VoitureViewHolder, position: Int) {
        val voiture = voitures[position]

        holder.textMarque.text = voiture.marque
        holder.textModele.text = voiture.modele

        // Affichage du favori
        holder.iconFavorite.visibility = if (voiture.isFavorite) View.VISIBLE else View.GONE

        // Compteur de photos (pour l'instant 1 photo par voiture)
        holder.textPhotoCount.text = "1"

        // TODO: Charger l'image de la voiture depuis le chemin
        // Pour l'instant, utiliser un placeholder
        holder.imageVoiture.setImageResource(R.drawable.ic_car_placeholder)

        // Click listener
        holder.itemView.setOnClickListener {
            onVoitureClick(voiture)
        }
    }

    override fun getItemCount(): Int = voitures.size

    fun updateVoitures(newVoitures: List<Voiture>) {
        voitures = newVoitures
        notifyDataSetChanged()
    }
}
