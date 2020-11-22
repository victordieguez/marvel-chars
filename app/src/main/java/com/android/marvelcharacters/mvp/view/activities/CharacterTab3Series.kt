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
import com.android.marvelcharacters.mvp.view.adapters.SeriesRecyclerViewAdapter
import com.android.marvelcharacters.mvp.view.adapters.SeriesRecyclerViewOnScrollListener
import com.android.marvelcharacters.network.dtos.MarvelSeries

class CharacterTab3Series : Fragment(), CharacterView {

    private lateinit var rootView: View
    private var characterPresenter: CharacterPresenter? = null
    private var adapter: SeriesRecyclerViewAdapter? = null

    companion object {
        fun newInstance(characterId: Long): CharacterTab3Series {
            val args = Bundle()
            val fragment = CharacterTab3Series()
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

            adapter = SeriesRecyclerViewAdapter()
            val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = GridLayoutManager(rootView.context, 2)
            recyclerView.adapter = adapter
            characterPresenter?.searchSeries(characterId, 0)
        }
        return rootView
    }

    fun onSeriesSearchSuccess(characterId: Long, series: List<MarvelSeries>, offset: Int, count: Int, total: Int) {
        if (offset == 0) {
            adapter?.setSeries(series)
        } else {
            adapter?.addSeries(series)
        }
        adapter?.notifyDataSetChanged()

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.clearOnScrollListeners()
        if (characterPresenter != null) {
            recyclerView.addOnScrollListener(SeriesRecyclerViewOnScrollListener(characterPresenter!!, characterId, offset, count, total))
        }
    }

    fun onSeriesSearchFailure() {
        rootView.findViewById<RecyclerView>(R.id.recyclerView).adapter = SeriesRecyclerViewAdapter()
        Toast.makeText(rootView.context, getString(R.string.characters_load_error), Toast.LENGTH_SHORT).show()
    }
}