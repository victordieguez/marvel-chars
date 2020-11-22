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
import com.android.marvelcharacters.network.dtos.MarvelSeries
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class SeriesRecyclerViewAdapter() : RecyclerView.Adapter<SeriesRecyclerViewAdapter.ViewHolder>() {

    private var series = mutableListOf<MarvelSeries>()
    private var context: Context? = null

    fun setSeries(series: List<MarvelSeries>) {
        this.series.clear()
        addSeries(series)
    }

    fun addSeries(series: List<MarvelSeries>) {
        this.series.addAll(series)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.series_row_layout, parent, false)
        return ViewHolder(viewHolder);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(series[position])
    }

    override fun getItemCount(): Int {
        return series.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val linearLayout = itemView.findViewById<LinearLayout>(R.id.linearLayout)
        private val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        fun bind(series: MarvelSeries) {
            linearLayout.tag = series.id
            titleTextView.text = series.title
            Glide.with(itemView.context).load(series.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
        }
    }
}