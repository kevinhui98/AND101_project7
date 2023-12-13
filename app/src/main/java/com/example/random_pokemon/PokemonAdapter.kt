package com.example.random_pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter(private val pokemonList: List<pokemon>): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView
        val name: TextView
        val id: TextView
        init {
            image = view.findViewById(R.id.pokemon_image)
            name = view.findViewById(R.id.pokemon_name)
            id = view.findViewById(R.id.pokemon_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = pokemonList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pokemonList.get(position)
        holder.name.text = item.name
        holder.id.text = item.id

        Glide.with(holder.itemView)
            .load(pokemonList[position].image)
            .fitCenter()
            .into(holder.image)
    }
}