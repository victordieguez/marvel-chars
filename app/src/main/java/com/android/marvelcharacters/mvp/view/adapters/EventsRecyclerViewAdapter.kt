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
import com.android.marvelcharacters.network.dtos.MarvelEvent
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class EventsRecyclerViewAdapter() : RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder>() {

    private var events = mutableListOf<MarvelEvent>()
    private var context: Context? = null

    fun setEvents(events: List<MarvelEvent>) {
        this.events.clear()
        addEvents(events)
    }

    fun addEvents(events: List<MarvelEvent>) {
        this.events.addAll(events)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.event_row_layout, parent, false)
        return ViewHolder(viewHolder);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val linearLayout = itemView.findViewById<LinearLayout>(R.id.linearLayout)
        private val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        fun bind(event: MarvelEvent) {
            linearLayout.tag = event.id
            titleTextView.text = event.title
            Glide.with(itemView.context).load(event.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
        }
    }
}