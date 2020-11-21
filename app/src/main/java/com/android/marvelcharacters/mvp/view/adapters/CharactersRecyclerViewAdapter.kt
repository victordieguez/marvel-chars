package com.android.marvelcharacters.mvp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelcharacters.R
import com.android.marvelcharacters.network.dtos.MarvelCharacter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class CharactersRecyclerViewAdapter(private val characters: List<MarvelCharacter>) : RecyclerView.Adapter<CharactersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.character_row_layout, parent, false)
        return ViewHolder(viewHolder);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val characterImageView = itemView.findViewById<ImageView>(R.id.characterImageView)
        private val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)

        fun bind(character: MarvelCharacter) {
            Glide.with(itemView.context).load(character.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(characterImageView)
            nameTextView.text = character.name
        }
    }
}