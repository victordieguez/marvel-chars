package com.android.marvelcharacters.mvp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelcharacters.R
import com.android.marvelcharacters.network.dtos.MarvelComic
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ComicsRecyclerViewAdapter() : RecyclerView.Adapter<ComicsRecyclerViewAdapter.ViewHolder>() {

    private var comics = mutableListOf<MarvelComic>()
    private var context: Context? = null

    fun setComics(comics: List<MarvelComic>) {
        this.comics.clear()
        addComics(comics)
    }

    fun addComics(comics: List<MarvelComic>) {
        this.comics.addAll(comics)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.comic_row_layout, parent, false)
        return ViewHolder(viewHolder);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comics[position])
    }

    override fun getItemCount(): Int {
        return comics.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val linearLayout = itemView.findViewById<LinearLayout>(R.id.linearLayout)
        private val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        fun bind(comic: MarvelComic) {
            linearLayout.tag = comic.id
            titleTextView.text = comic.title
            Glide.with(itemView.context).load(comic.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
        }
    }
}