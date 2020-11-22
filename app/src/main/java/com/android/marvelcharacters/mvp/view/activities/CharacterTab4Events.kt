package com.android.marvelcharacters.mvp.view.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.presenter.CharacterPresenter
import com.android.marvelcharacters.mvp.view.CharacterView
import com.android.marvelcharacters.mvp.view.adapters.EventsRecyclerViewAdapter
import com.android.marvelcharacters.mvp.view.adapters.EventsRecyclerViewOnScrollListener
import com.android.marvelcharacters.network.dtos.MarvelEvent

class CharacterTab4Events : Fragment(), CharacterView {

    private lateinit var rootView: View
    private var characterPresenter: CharacterPresenter? = null
    private var adapter: EventsRecyclerViewAdapter? = null

    companion object {
        fun newInstance(characterId: Long): CharacterTab4Events {
            val args = Bundle()
            val fragment = CharacterTab4Events()
            args.putLong("characterId", characterId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val characterId = arguments?.getLong("characterId")
        rootView = inflater.inflate(R.layout.character_tab2, container, false)
        if (context != null && characterId != null) {
            characterPresenter = CharacterPresenter(this, context!!)

            adapter = EventsRecyclerViewAdapter()
            val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = GridLayoutManager(rootView.context, 2)
            recyclerView.adapter = adapter
            characterPresenter?.searchEvents(characterId, 0)
        }
        return rootView
    }

    /**
     * Listener called when any events search returns data successfully
     * @param events New events loaded from the API to be added to the list
     * @param offset Number used to identify the position of the first event of this list in the total amount (if it's 0 we are on the first page, if not the second page has been loaded)
     * @param count Number of events returned from the API
     * @param total Total amount of events that match with the current query
     */
    fun onEventsSearchSuccess(characterId: Long, events: List<MarvelEvent>, offset: Int, count: Int, total: Int) {
        if (offset == 0) {
            adapter?.setEvents(events)
        } else {
            adapter?.addEvents(events)
        }
        adapter?.notifyDataSetChanged()

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.clearOnScrollListeners()
        if (characterPresenter != null) {
            recyclerView.addOnScrollListener(EventsRecyclerViewOnScrollListener(characterPresenter!!, characterId, offset, count, total))
        }
    }

    /**
     * Listener called in case of any error in events search
     */
    fun onEventsSearchFailure() {
        rootView.findViewById<RecyclerView>(R.id.recyclerView).adapter = EventsRecyclerViewAdapter()
        Toast.makeText(rootView.context, getString(R.string.events_load_error), Toast.LENGTH_SHORT).show()
    }
}