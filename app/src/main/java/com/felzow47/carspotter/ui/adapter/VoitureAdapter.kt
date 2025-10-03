package com.felzow47.carspotter.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.felzow47.carspotter.R
import com.felzow47.carspotter.data.entity.Voiture

class VoitureAdapter(
    private val onItemClick: (Voiture) -> Unit
) : ListAdapter<Voiture, VoitureAdapter.VoitureViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoitureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_voiture_simple, parent, false)
        return VoitureViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: VoitureViewHolder, position: Int) {
        val voiture = getItem(position)
        holder.bind(voiture)
    }

    class VoitureViewHolder(
        itemView: View,
        private val onItemClick: (Voiture) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val marqueModele: TextView = itemView.findViewById(R.id.text_marque_modele)
        private val plaque: TextView = itemView.findViewById(R.id.text_plaque)
        private val date: TextView = itemView.findViewById(R.id.text_date)

        fun bind(voiture: Voiture) {
            marqueModele.text = "${voiture.marque} ${voiture.modele}"
            plaque.text = "📋 ${voiture.immatriculation ?: "Non renseigné"}"
            date.text = "📅 ${voiture.dateObservation ?: voiture.dateCreation}"

            itemView.setOnClickListener { onItemClick(voiture) }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Voiture>() {
            override fun areItemsTheSame(oldItem: Voiture, newItem: Voiture): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Voiture, newItem: Voiture): Boolean {
                return oldItem == newItem
            }
        }
    }
}
