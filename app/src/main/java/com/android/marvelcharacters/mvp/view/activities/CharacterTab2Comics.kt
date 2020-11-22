package com.android.marvelcharacters.mvp.view.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelcharacters.R
import com.android.marvelcharacters.mvp.presenter.CharacterPresenter
import com.android.marvelcharacters.mvp.view.CharacterView
import com.android.marvelcharacters.mvp.view.adapters.CharactersRecyclerViewAdapter
import com.android.marvelcharacters.mvp.view.adapters.ComicsRecyclerViewAdapter
import com.android.marvelcharacters.mvp.view.adapters.ComicsRecyclerViewOnScrollListener
import com.android.marvelcharacters.network.dtos.MarvelComic

class CharacterTab2Comics : Fragment(), CharacterView {

    private lateinit var rootView: View
    private var characterPresenter: CharacterPresenter? = null
    private var adapter: ComicsRecyclerViewAdapter? = null

    companion object {
        fun newInstance(characterId: Long): CharacterTab2Comics {
            val args = Bundle()
            val fragment = CharacterTab2Comics()
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

            adapter = ComicsRecyclerViewAdapter()
            val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = GridLayoutManager(rootView.context, 2)
            recyclerView.adapter = adapter
            characterPresenter?.searchComics(characterId, 0)
        }
        return rootView
    }

    fun onComicsSearchSuccess(characterId: Long, comics: List<MarvelComic>, offset: Int, count: Int, total: Int) {
        if (offset == 0) {
            adapter?.setComics(comics)
        } else {
            adapter?.addComics(comics)
        }
        adapter?.notifyDataSetChanged()

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.clearOnScrollListeners()
        if (characterPresenter != null) {
            recyclerView.addOnScrollListener(ComicsRecyclerViewOnScrollListener(characterPresenter!!, characterId, offset, count, total))
        }
        rootView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
    }

    fun onComicsSearchFailure() {
        rootView.findViewById<RecyclerView>(R.id.recyclerView).adapter = CharactersRecyclerViewAdapter()
        rootView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        Toast.makeText(rootView.context, getString(R.string.characters_load_error), Toast.LENGTH_SHORT).show()
    }
}